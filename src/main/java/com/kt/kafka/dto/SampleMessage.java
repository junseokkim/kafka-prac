package com.kt.kafka.dto;

public record SampleMessage(
        String sender,
        String content
) {}