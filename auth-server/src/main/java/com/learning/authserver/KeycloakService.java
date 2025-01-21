package com.learning.authserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class KeycloakService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakAuthServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    public AuthResponse authenticateUser(String username, String password) {
        String url = String.format("%s/realms/%s/protocol/openid-connect/token", keycloakAuthServerUrl, realm);

        RestTemplate restTemplate = new RestTemplate();

        // Prepare the request body for authentication
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("username", username);
        body.add("password", password);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
        // Send the POST request
        ResponseEntity<AuthResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, AuthResponse.class);

        // If the status code is 200 OK, return the access token
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println(response.toString());
            return response.getBody();  // This is the JSON response, which will contain the access token
        } else {
            throw new RuntimeException("Authentication failed: " + response.getStatusCode());
        }
    }

    public String registerUser(UserCredentials userDTO) {
        // Obtain access token for admin user
        System.out.println(userDTO.getUsername());
        String accessToken = getAdminAccessToken();

        // Create the new user in Keycloak
        String url = String.format("%s/admin/realms/%s/users", keycloakAuthServerUrl, realm);

        RestTemplate restTemplate = new RestTemplate();

        // Prepare the user data
//        MultiValueMap<String, Object> user = new LinkedMultiValueMap<>();
//        user.add("username", userDTO.getUsername());
//        user.add("enabled", true);
//        user.add("email", userDTO.getEmail());
//        user.add("credentials", new Object[]{
//                Map.of("type", "password", "value", userDTO.getPassword(), "temporary", false)
//        });

        String user = "{\n" +
                "  \"username\": \"" + userDTO.getUsername() + "\",\n" +
                "  \"enabled\": true,\n" +
                "  \"email\": \"" + userDTO.getEmail() + "\",\n" +
                "  \"firstName\": \"New\",\n" +
                "  \"lastName\": \"User\",\n" +
                "  \"credentials\": [\n" +
                "    {\n" +
                "      \"type\": \"password\",\n" +
                "      \"value\": \"" + userDTO.getPassword() + "\",\n" +
                "      \"temporary\": false\n" +
                "    }\n" +
                "  ]\n" +
                "}";



        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);

        // Send POST request to create the user
        HttpEntity<String> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // Check if the user was created successfully
        if (response.getStatusCode() == HttpStatus.CREATED) {
            return "User registered successfully!";
        } else {
            return "User registration failed: " + response.getBody();
        }
    }

    private String getAdminAccessToken() {
        String url = keycloakAuthServerUrl + "/realms/master"  + "/protocol/openid-connect/token";

        RestTemplate restTemplate = new RestTemplate();

        // Prepare the request body for obtaining an admin access token
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", "admin");
        body.add("password", "admin");
        body.add("client_id", "admin-cli");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        // Send the request to obtain an access token
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
        // Return the access token from the response
        return (String) response.getBody().get("access_token");
    }
}
