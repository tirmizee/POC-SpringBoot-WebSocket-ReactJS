package com.tirmizee.webapi;

import com.tirmizee.websocket.messages.GreetingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/notify/{payload}")
    public String notification(@PathVariable String payload) {
        GreetingMessage message = new GreetingMessage("C01", payload);
        simpMessagingTemplate.convertAndSend("/topic/greetings", message);
        return "success";
    }

}
