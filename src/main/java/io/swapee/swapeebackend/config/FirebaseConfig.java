package io.swapee.swapeebackend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        // serviceAccount.json is the file downloaded from Firebase console
        // TODO: Replace the path to your service account
        FileInputStream serviceAccount = new FileInputStream("/home/ragul/Documents/My/firebase-oauth/src/main/resources/service-account.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
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