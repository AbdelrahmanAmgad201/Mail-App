package com.example.backend;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {


    @MessageMapping("/enqueue")
    public void handleMessage(){
        // logic for handling
    }
}
