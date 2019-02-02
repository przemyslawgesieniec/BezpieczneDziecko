package pl.project.saveKid.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfiguration
{
    @Bean
    public FirebaseApp firebaseApp() throws IOException
    {
        FileInputStream serviceAccount = new FileInputStream("bezpiecznedziecko-69817-firebase-adminsdk-tn2gi-9a9490cab0.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://bezpiecznedziecko-69817.firebaseio.com/")
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp)
    {
        return FirebaseMessaging.getInstance(firebaseApp);
    }
}
