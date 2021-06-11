package pl.training.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.HashMap;
import java.util.Map;

@Controller
@Log
@RequiredArgsConstructor
public class ChatSocketController implements ApplicationListener<SessionConnectedEvent> {

    private final SimpMessagingTemplate messagingTemplate;
    private final Map<String, String> sockets = new HashMap<>();

    @Override
    public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
        log.info(sessionConnectedEvent.toString());
    }

    @MessageMapping("/chat")
    @SendTo("/chat-topic/messages")
    public Message send(Message message) {
        log.info("New message " + message);
        return message;
    }

}
