package com.tirmizee.websocket;

import com.tirmizee.websocket.messages.GreetingMessage;
import com.tirmizee.websocket.messages.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public GreetingMessage greeting(HelloMessage message) throws Exception {
        return new GreetingMessage("C01", message.getContent());
    }

}
