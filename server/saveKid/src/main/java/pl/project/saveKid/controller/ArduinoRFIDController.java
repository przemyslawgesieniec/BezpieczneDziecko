package pl.project.saveKid.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArduinoRFIDController {


    @GetMapping("/get/{rfidId}")
    public @ResponseBody
    ResponseEntity<String> getRFID(@PathVariable String rfidId) {
        System.out.println("rfidId: " + rfidId);
        return new ResponseEntity<String>("GET Response", HttpStatus.OK);
    }
}
