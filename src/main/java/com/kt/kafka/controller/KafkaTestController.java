package com.kt.kafka.controller;

import com.kt.kafka.dto.SampleMessage;
import com.kt.kafka.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaTestController {

    private final KafkaProducerService kafkaProducer;

    public KafkaTestController(KafkaProducerService kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/string")
    public ResponseEntity<String> sendString(
            @RequestParam String topic,
            @RequestBody String message
    ) {
        kafkaProducer.sendString(topic, message);
        return ResponseEntity.ok("✅ STRING 전송 완료");
    }

    @PostMapping("/json")
    public ResponseEntity<String> sendJson(
            @RequestParam String topic,
            @RequestBody SampleMessage message
    ) {
        kafkaProducer.sendJson(topic, message);
        return ResponseEntity.ok("✅ JSON 전송 완료");
    }
}

