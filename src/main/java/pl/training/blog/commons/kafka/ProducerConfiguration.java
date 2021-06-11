package pl.training.blog.commons.kafka;

import lombok.extern.java.Log;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;

import java.util.HashMap;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Log
@Configuration
public class ProducerConfiguration {

    @Bean
    public ProducerFactory<String, String> producerFactory(@Value("${kafka.server}") String server) {
        var properties = new HashMap<String, Object>();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, server);
        properties.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        var kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setProducerListener(new ProducerListener<>() {

            @Override
            public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
                log.info("Message sent");
                ProducerListener.super.onSuccess(producerRecord, recordMetadata);
            }

            @Override
            public void onError(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata, Exception exception) {
                log.info("Error sending message: " + exception.getMessage());
                ProducerListener.super.onError(producerRecord, recordMetadata, exception);
            }

        });
        return kafkaTemplate;
    }

    public NewTopic mainTopic(@Value("kafka.mainTopic") String mainTopic) {
        return TopicBuilder.name(mainTopic).build();
    }

}
