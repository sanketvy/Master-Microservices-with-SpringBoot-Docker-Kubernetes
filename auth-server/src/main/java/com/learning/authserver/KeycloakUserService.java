package com.learning.authserver;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeycloakUserService {

    @Autowired
    private Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    public void createUser(String username, String password, String email) {
        RealmResource realmResource = keycloak.realm(keycloakRealm);

        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEnabled(true);
        user.setEmail(email);
        user.setFirstName("First");
        user.setLastName("Last");
        
        // Set the credentials
        List<CredentialRepresentation> credentials = new ArrayList<>();
        CredentialRepresentation passwordCredential = new CredentialRepresentation();
        passwordCredential.setType(CredentialRepresentation.PASSWORD);
        passwordCredential.setValue(password);
        passwordCredential.setTemporary(false); // This will make the password permanent
        credentials.add(passwordCredential);
        user.setCredentials(credentials);

        System.out.println(user);

        // Create the user
        realmResource.users().create(user);

        // Now get the user ID (you can also get it by querying)
        String userId = getUserIdByUsername(username);
        // Set the emailVerified attribute to true
        setEmailVerified(userId);
    }

    private String getUserIdByUsername(String username) {
        RealmResource realmResource = keycloak.realm(keycloakRealm);
        List<UserRepresentation> users = realmResource.users().search(username);

        if (!users.isEmpty()) {
            return users.get(0).getId();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    private void setEmailVerified(String userId) {
        RealmResource realmResource = keycloak.realm(keycloakRealm);
        UserResource userResource = realmResource.users().get(userId);
        UserRepresentation user = userResource.toRepresentation();
        user.setEmailVerified(true);
        userResource.update(user);
    }
}
