package io.swapee.swapeebackend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        // Load service account JSON file as an InputStream
        InputStream serviceAccountStream = getClass().getResourceAsStream("/service-account.json");

        if (serviceAccountStream == null) {
            throw new RuntimeException("Firebase service account file not found. Make sure it exists in the resources directory.");
        }

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                .build();

        return FirebaseApp.initializeApp(options);
    }


    @Bean
    public FirebaseAuth firebaseAuth() {
        try {
            return FirebaseAuth.getInstance(firebaseApp());
        } catch (IOException e) {
            throw new RuntimeException("Error initializing FirebaseAuth", e);
        }
    }
}