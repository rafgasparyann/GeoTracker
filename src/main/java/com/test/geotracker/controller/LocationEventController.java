package com.test.geotracker.controller;

import com.test.geotracker.model.LocationEvent;
import com.test.geotracker.repository.LocationEventRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationEventController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final LocationEventRepository repository;

    public LocationEventController(KafkaTemplate<String, String> kafkaTemplate, LocationEventRepository repository) {
        this.kafkaTemplate = kafkaTemplate;
        this.repository = repository;
    }

    @PostMapping("/send")
    public String sendLocation(@RequestParam double latitude, @RequestParam double longitude, @RequestParam String userId) {
        String event = userId + "," + latitude + "," + longitude + "," + LocalDateTime.now();
        kafkaTemplate.send("location-topic", event);
        return "Location sent to Kafka!";
    }

    @GetMapping("/history/{userId}")
    public List<LocationEvent> getLocationHistory(@PathVariable String userId) {
        return repository.findByUserId(userId);
    }
}
