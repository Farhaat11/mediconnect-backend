package com.example.medicoonect_backend.m2.Repository;

import com.example.medicoonect_backend.m2.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
    List<Appointment> findByConsultationModeAndStatus(
        Appointment.ConsultationMode consultationMode,
        Appointment.AppointmentStatus status
    );
}
