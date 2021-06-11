package pl.training.blog.commons.kafka;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Configuration
public class ConsumerConfiguration {

    @Bean
    public ConsumerFactory<String, String> consumerFactory(@Value("${kafka.server}") String server) {
        var properties = new HashMap<String, Object>();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, server);
        properties.put(GROUP_ID_CONFIG, "training");
        properties.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
        var concurrentConsumerFactory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        concurrentConsumerFactory.setConsumerFactory(consumerFactory);
        return concurrentConsumerFactory;


    }

}
