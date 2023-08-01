package com.example.rest.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class KafkaServiceImpl implements KafkaService {

    private final CacheService cacheService;

    private final KafkaTemplate<JsonNode, JsonNode> kafkaTemplate;

    @Autowired
    public KafkaServiceImpl(CacheService cacheService,
                            KafkaTemplate<JsonNode, JsonNode> kafkaTemplate) {
        this.cacheService = cacheService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "${kafka.consumer.topics.topic2}")
    public void consumeMessageAndSave(String message) {
        log.info("consume message message from DB:{}", message);
        cacheService.saveMessage(message);
    }

    public void sendMessage(String topic, JsonNode operation, JsonNode value) {
        kafkaTemplate.send(topic, operation, value);
    }

}