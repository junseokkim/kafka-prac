package com.kt.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kt.kafka.dto.SampleMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {

    private final ObjectMapper objectMapper;

    public KafkaConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = {"string-topic", "json-topic"}, groupId = "${spring.application.name}")
    public void listen(String rawMessage) {
        System.out.println("📥 [RAW] Received: " + rawMessage);

        // JSON인지 판단해서 파싱 시도
        if (rawMessage.startsWith("{") && rawMessage.endsWith("}")) {
            try {
                SampleMessage message = objectMapper.readValue(rawMessage, SampleMessage.class);
                System.out.println("[JSON] Parsed: " + message);
            } catch (Exception e) {
                System.out.println("⚠️ [WARNING] JSON 파싱 실패. 문자열 처리됨.");
            }
        } else {
            System.out.println("[STRING] Message: " + rawMessage);
        }
    }
}