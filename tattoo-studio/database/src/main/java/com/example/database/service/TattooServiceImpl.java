package com.example.database.service;

import com.example.database.model.TattooAppointment;
import com.example.database.repository.TattooAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TattooServiceImpl implements TattooService {

    private final TattooAppointmentRepository tattooAppointmentRepository;

    @Autowired
    public TattooServiceImpl(TattooAppointmentRepository tattooAppointmentRepository) {
        this.tattooAppointmentRepository = tattooAppointmentRepository;
    }

    @Override
    public Optional<TattooAppointment> findAppointmentById(Long appointmentId) {

        return tattooAppointmentRepository.findById(appointmentId);
    }

    @Override
    public List<TattooAppointment> findAllAppointment() {
        return tattooAppointmentRepository.findAll();
    }

    @Transactional
    @Override
    public TattooAppointment addAppointment(TattooAppointment tattooAppointment) {
        return tattooAppointmentRepository.save(tattooAppointment);
    }

    @Override
    public void deleteAppointmentById(Long id) {
        tattooAppointmentRepository.deleteById(id);
    }
}
