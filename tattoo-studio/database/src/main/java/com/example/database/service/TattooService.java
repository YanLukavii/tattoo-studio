package com.example.database.service;

import com.example.database.model.TattooAppointment;

import java.util.List;
import java.util.Optional;

public interface TattooService {

    Optional<TattooAppointment> findAppointmentById(Long appointmentId);

    List<TattooAppointment> findAllAppointment();

    TattooAppointment addAppointment(TattooAppointment tattooAppointment);
    void deleteAppointmentById(Long id);

}
