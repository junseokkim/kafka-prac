package com.kt.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    // 문자열 직접 전송
    public void sendString(String topic, String message) {
        kafkaTemplate.send(topic, message);
        System.out.println("[STRING] Sent to " + topic + ": " + message);
    }

    // 객체 → JSON 문자열로 전송
    public void sendJson(String topic, Object data) {
        try {
            String json = objectMapper.writeValueAsString(data);
            kafkaTemplate.send(topic, json);
            System.out.println("[JSON] Sent to " + topic + ": " + json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON 직렬화 실패", e);
        }
    }
}
