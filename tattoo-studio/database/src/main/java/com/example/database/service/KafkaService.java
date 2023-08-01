package com.example.database.service;

import com.example.database.model.CrudOperation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaService {

    private final String topic;

    private final KafkaTemplate<String, JsonNode> kafkaTemplate;

    private final ObjectMapper objectMapper;

    private final EntityConversionService entityConversionService;


    public KafkaService(@Value("${kafka.consumer.topics.topic2}") String topic,
                        KafkaTemplate<String, JsonNode> kafkaTemplate,
                        ObjectMapper objectMapper,
                        EntityConversionService entityConversionService) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.entityConversionService = entityConversionService;
    }

    @KafkaListener(topics = "${kafka.consumer.topics.topic1}")
    public void consumeFromTopicOne(ConsumerRecord<String, String> record) {
        log.info("consume CRUD OPERATION:{} from CONTROLLER message:{}", record.key(), record.value());

        CrudOperation crudOperation = convertKeyToCrudOperation(record.key());

        kafkaTemplate.send(topic,
                entityConversionService.convertResponseToRest(crudOperation, record.value()));
    }

    private CrudOperation convertKeyToCrudOperation(String recordKey) {
        CrudOperation crudOperation;
        try {
            crudOperation = objectMapper.convertValue(objectMapper.readTree(recordKey), CrudOperation.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getCause());
        }
        return crudOperation;
    }

}