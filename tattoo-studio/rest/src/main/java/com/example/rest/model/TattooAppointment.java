package com.example.rest.model;

import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TattooAppointment {

    private Long id;

    @Nonnull
    private String clientName;

    @Nonnull
    private String tattooType;

    @Nonnull
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