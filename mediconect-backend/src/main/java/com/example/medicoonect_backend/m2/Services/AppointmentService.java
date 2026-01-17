package com.example.medicoonect_backend.m2.Services;

import com.example.medicoonect_backend.m2.Repository.AppointmentRepository;
import com.example.medicoonect_backend.m2.entity.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Transactional
    public Appointment createAppointment(Appointment appointment) {
        if (appointment.getStatus() == null) {
            appointment.setStatus(Appointment.AppointmentStatus.UPCOMING);
        }
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByConsultationMode(Appointment.ConsultationMode consultationMode) {
        return appointmentRepository.findByConsultationMode(consultationMode);
    }

    @Transactional
    public Appointment updateAppointmentStatus(Long id, Appointment.AppointmentStatus status) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            appointment.setStatus(status);
            return appointmentRepository.save(appointment);
        }
        throw new RuntimeException("Appointment not found with id: " + id);
    }

    public List<Appointment> getVirtualUpcomingAppointments() {
        return appointmentRepository.findByConsultationModeAndStatus(
            Appointment.ConsultationMode.VIRTUAL,
            Appointment.AppointmentStatus.UPCOMING
        );
    }
}
