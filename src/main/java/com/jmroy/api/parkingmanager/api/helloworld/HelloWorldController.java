package com.jmroy.api.parkingmanager.api.helloworld;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
class Message {
    private String text;
}

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public Message hello() {
        Message message = new Message();
        message.setText("Hello, World!");
        return message;
    }
}