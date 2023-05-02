package com.example.rest.service;

import com.fasterxml.jackson.databind.JsonNode;

public interface KafkaService {
    void sendMessage(String topic, JsonNode operation, JsonNode value);
}
