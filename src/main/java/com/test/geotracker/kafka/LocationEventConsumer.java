package com.test.geotracker.kafka;

import com.test.geotracker.model.LocationEvent;
import com.test.geotracker.repository.LocationEventRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LocationEventConsumer {

    private final LocationEventRepository repository;

    public LocationEventConsumer(LocationEventRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "location-topic", groupId = "geo-tracker-group")
    public void consumeLocationEvent(String message) {
        String[] data = message.split(",");
        if (data.length != 4) return;

        LocationEvent event = new LocationEvent(
                data[0], // userId
                Double.parseDouble(data[1]), // latitude
                Double.parseDouble(data[2]), // longitude
                LocalDateTime.parse(data[3]) // timestamp
        );

        repository.save(event);
        System.out.println("Saved location event: " + event);
    }
}
