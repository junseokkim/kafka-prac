package com.kt.kafka.controller;

import com.kt.kafka.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaTestController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam String message) {
        kafkaProducerService.sendMessage("test-topic", message);
        return ResponseEntity.ok("Message sent!");
    }
}

