package com.example.rest.service;

import com.example.rest.model.TattooAppointment;

import java.util.List;

public interface TattooAppointmentService {

    TattooAppointment create(TattooAppointment appointment);

    TattooAppointment findAppointmentById(Long id);

    List<TattooAppointment> getAllAppointment();

    String deleteById(Long id);


}
