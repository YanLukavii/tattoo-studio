package com.example.database.service;

import com.example.database.model.CrudOperation;
import com.fasterxml.jackson.databind.JsonNode;

public interface EntityConversionService {

    JsonNode convertResponseToRest(CrudOperation crudOperation, String recordValue);

}
