package com.example.rest.service;

import com.example.rest.model.CrudOperation;
import com.example.rest.model.TattooAppointment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TattooAppointmentServiceImpl implements TattooAppointmentService {

    private final KafkaService kafkaService;

    private final CacheService cacheService;

    private final String topic;

    private final ObjectMapper objectMapper;

    @Autowired
    public TattooAppointmentServiceImpl(KafkaService kafkaService,
                                        CacheService cacheService,
                                        @Value("${kafka.consumer.topics.topic1}") String topic,
                                        ObjectMapper objectMapper) {
        this.kafkaService = kafkaService;
        this.cacheService = cacheService;
        this.topic = topic;
        this.objectMapper = objectMapper;
    }

    public TattooAppointment create(TattooAppointment appointment) {

        kafkaService.sendMessage(topic, objectMapper.convertValue(CrudOperation.CREATE, JsonNode.class),
                objectMapper.convertValue(appointment, JsonNode.class));

        sleepWhileDequeEmpty(1000);

        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(cacheService.pollMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getCause());
        }

        return objectMapper.convertValue(jsonNode, TattooAppointment.class);
    }


    public TattooAppointment findAppointmentById(Long id) {

        kafkaService.sendMessage(topic, objectMapper.convertValue(CrudOperation.GET_BY_ID, JsonNode.class),
                objectMapper.convertValue(id, JsonNode.class));

        sleepWhileDequeEmpty(1000);

        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(cacheService.pollMessage());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getCause());
        }

        return Optional.ofNullable(objectMapper.convertValue(jsonNode, TattooAppointment.class))
                .orElse(null);
    }


    public List<TattooAppointment> getAllAppointment() {

        kafkaService.sendMessage(topic, objectMapper.convertValue(CrudOperation.READ_ALL, JsonNode.class),
                objectMapper.convertValue("", JsonNode.class));

        sleepWhileDequeEmpty(1000);

        List<TattooAppointment> list;
        try {
            list = objectMapper.readValue(cacheService.pollMessage(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getCause());
        }

        return list;
    }

    @Override
    public String deleteById(Long id) {
        kafkaService.sendMessage(topic, objectMapper.convertValue(CrudOperation.DELETE, JsonNode.class),
                objectMapper.convertValue(id, JsonNode.class));

        sleepWhileDequeEmpty(1000);

        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(cacheService.pollMessage());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getCause());
        }

        return "Deleted: " + id;
    }

    private void sleepWhileDequeEmpty(long millis) {

        while (cacheService.isEmpty()) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getCause());
            }
        }
    }
}