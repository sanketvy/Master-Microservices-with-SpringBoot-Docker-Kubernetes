package com.learning.authserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private KeycloakService keycloakService;

    @Autowired
    private KeycloakUserService keycloakUserService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserCredentials userCredentials) {
        try {
            return ResponseEntity.ok(keycloakService.authenticateUser(userCredentials.getUsername(), userCredentials.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser( @RequestBody UserCredentials userDTO) {
        try {
            keycloakUserService.createUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
//            String message = keycloakService.registerUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Registration failed: " + e.getMessage());
        }
    }

}
