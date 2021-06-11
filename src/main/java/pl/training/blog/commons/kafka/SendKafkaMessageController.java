package pl.training.blog.commons.kafka;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.training.blog.commons.mail.MailMessage;
import pl.training.blog.commons.mail.MailService;
import pl.training.blog.commons.mail.TextSource;

import java.time.Instant;
import java.util.Locale;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SendKafkaMessageController {

    @Value("${kafka.mainTopic}")
    @Setter
    private String mainTopic;
    private final KafkaProducer kafkaProducer;

    @GetMapping("kafka-messages")
    public ResponseEntity<Void> send() {
        kafkaProducer.send("Test message " + Instant.now(), mainTopic);
        return ResponseEntity.accepted().build();
    }

}
