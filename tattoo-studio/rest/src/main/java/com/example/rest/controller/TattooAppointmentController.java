package com.example.rest.controller;

import com.example.rest.model.TattooAppointment;
import com.example.rest.service.TattooAppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/appointments")
public class TattooAppointmentController {

    private final TattooAppointmentService tattooAppointmentService;

    @Autowired
    public TattooAppointmentController(TattooAppointmentService tattooAppointmentService) {
        this.tattooAppointmentService = tattooAppointmentService;
    }

    @PostMapping
    public TattooAppointment create(@RequestBody TattooAppointment appointment) {
        return tattooAppointmentService.create(appointment);
    }

    @GetMapping("/{id}")
    public TattooAppointment findAppointmentById(@PathVariable Long id) {
        return tattooAppointmentService.findAppointmentById(id);
    }

    @GetMapping
    public ResponseEntity<List<TattooAppointment>> getAllAppointment() {
        return ResponseEntity.ok(tattooAppointmentService.getAllAppointment());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteAppointment(@PathVariable Long id) {
        tattooAppointmentService.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


}