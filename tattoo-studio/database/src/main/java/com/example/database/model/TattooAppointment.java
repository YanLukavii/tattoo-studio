package com.example.database.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tattoo_appointments")
@NoArgsConstructor
public class TattooAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    @Column(name = "client_name")
    private String clientName;

    @Nonnull
    @Column(name = "tattoo_type")
    private String tattooType;

    @Nonnull
    @Column(name = "appointment_time")
    private LocalDateTime appointmentTime;

    public TattooAppointment(Long id,
                             @Nonnull String clientName,
                             @Nonnull String tattooType,
                             @Nonnull LocalDateTime appointmentTime) {
        this.id = id;
        this.clientName = clientName;
        this.tattooType = tattooType;
        this.appointmentTime = appointmentTime;
    }
}