package pl.training.blog.payments.adapters.jee;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@RequiredArgsConstructor
@Service
public class JmsSender {

    private final JmsTemplate jmsTemplate;
    private final Queue messagesQueue;

    public void send(String text) {
        jmsTemplate.send(messagesQueue, session -> session.createTextMessage(text));
    }

}
