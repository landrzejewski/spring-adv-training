package pl.training.blog.commons.kafka;

import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log
public class KafkaConsumer {

    @KafkaListener(topics = "main-topic")
    public void onMessage(String message) {
        log.info("New message: " + message);
    }

}
