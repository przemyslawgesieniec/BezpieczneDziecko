package pl.project.saveKid.controller;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class ArduinoRFIDController
{
    @Value("${firebase.token}")
    private String token;

    @Value("${firebase.auth_key}")
    private String authKey;

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @GetMapping("/get/{rfidId}")
    public @ResponseBody ResponseEntity<String> getRFID(@PathVariable String rfidId) throws FirebaseMessagingException
    {
        String send = firebaseMessaging.send(prepareMessage());

        System.out.println(rfidId);

        return new ResponseEntity<>("GET Response", HttpStatus.OK);
    }

    private Message prepareMessage()
    {
        return Message
                .builder()
                .setToken(this.token)
                .putData("type", "common_msg")
                .putData("content", "Your child has just entered the school")
                .putData("sendTime", new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()))
                .setNotification(new Notification("Notification", "Your child has just entered the school"))
                .build()
        ;
    }
}
