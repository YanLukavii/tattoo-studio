package com.example.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.database.model.TattooAppointment;

@Repository
public interface TattooAppointmentRepository extends JpaRepository<TattooAppointment, Long> {
}