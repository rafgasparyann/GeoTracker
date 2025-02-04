package com.test.geotracker.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "location-topic", groupId = "location-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
