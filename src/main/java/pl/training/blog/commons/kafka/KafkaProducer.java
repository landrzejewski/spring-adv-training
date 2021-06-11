package pl.training.blog.commons.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Log
@RequiredArgsConstructor
public class KafkaProducer implements ListenableFutureCallback<SendResult<String, String>> {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message, String topicName) {
        kafkaTemplate.send(topicName, message).addCallback(this);
    }

    @Override
    public void onFailure(Throwable throwable) {
        log.info(throwable.getMessage());
    }

    @Override
    public void onSuccess(SendResult<String, String> result) {
        log.info(result.toString());
    }

}
