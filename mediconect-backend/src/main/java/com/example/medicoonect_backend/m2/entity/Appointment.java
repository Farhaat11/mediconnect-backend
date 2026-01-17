package com.example.medicoonect_backend.m2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patientName;

    @Column(nullable = false)
    private String patientPhone;

    @Column(nullable = false)
    private String patientEmail;

    @Column(nullable = false)
    private Integer patientAge;

    @Column(nullable = false)
    private String doctorSpecialization;

    @Column(nullable = false)
    private String doctorName;

    @Column(nullable = false)
    private LocalDate appointmentDate;

    @Column(nullable = false)
    private LocalTime timeSlot;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConsultationMode consultationMode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    public enum ConsultationMode {
        VIRTUAL,
        IN_PERSON
    }

    public enum AppointmentStatus {
        UPCOMING,
        COMPLETED,
        CANCELLED
    }
}
