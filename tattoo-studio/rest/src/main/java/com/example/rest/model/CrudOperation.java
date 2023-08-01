package com.example.rest.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CrudOperation {
    CREATE("Create new TattooAppointment"),
    READ_ALL("Read ALL TattooAppointment"),
    GET_BY_ID("Find TattooAppointment by id"),
    DELETE("Delete by id");

    String description;
}