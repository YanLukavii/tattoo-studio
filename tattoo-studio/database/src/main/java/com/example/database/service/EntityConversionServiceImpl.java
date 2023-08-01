package com.example.database.service;

import com.example.database.model.CrudOperation;
import com.example.database.model.TattooAppointment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntityConversionServiceImpl implements EntityConversionService {

   private final TattooService tattooService;

   private final ObjectMapper objectMapper;

    @Autowired
    public EntityConversionServiceImpl(TattooService tattooService, ObjectMapper objectMapper) {
        this.tattooService = tattooService;
        this.objectMapper = objectMapper;
    }

    @Override
    public JsonNode convertResponseToRest(CrudOperation crudOperation, String recordValue) {

        JsonNode jsonNodeResult = switch (crudOperation) {

            case READ_ALL -> {
                List<TattooAppointment> list = tattooService.findAllAppointment();
                yield objectMapper.convertValue(list, JsonNode.class);
            }
            case CREATE -> {
                JsonNode jsonNode;
                try {
                    jsonNode = objectMapper.readTree(recordValue);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e.getCause());
                }
                TattooAppointment appointment = objectMapper.convertValue(jsonNode, TattooAppointment.class);
                tattooService.addAppointment(appointment);
                yield jsonNode;
            }
            case GET_BY_ID -> {
                Long id = Long.valueOf(recordValue);
                TattooAppointment tattooAppointment = null;
                Optional<TattooAppointment> appointment = tattooService.findAppointmentById(id);
                if (appointment.isPresent()) {
                    tattooAppointment = appointment.get();
                }
                yield objectMapper.convertValue(tattooAppointment, JsonNode.class);
            }
            case DELETE -> {
                Long id = Long.valueOf(recordValue);
                 tattooService.deleteAppointmentById(id);
                yield objectMapper.convertValue("", JsonNode.class);
            }
        };
        return jsonNodeResult;
    }
}
