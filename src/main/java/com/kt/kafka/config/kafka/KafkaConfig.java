package com.kt.kafka.config.kafka;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
public class KafkaConfig {
    // 필요 시 ProducerFactory, ConsumerFactory, KafkaTemplate Bean 등록 가능
}