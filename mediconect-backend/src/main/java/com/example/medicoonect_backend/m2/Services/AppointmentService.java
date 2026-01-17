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

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    @Transactional
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
        
        existingAppointment.setPatientName(updatedAppointment.getPatientName());
        existingAppointment.setPatientPhone(updatedAppointment.getPatientPhone());
        existingAppointment.setPatientEmail(updatedAppointment.getPatientEmail());
        existingAppointment.setPatientAge(updatedAppointment.getPatientAge());
        existingAppointment.setDoctorSpecialization(updatedAppointment.getDoctorSpecialization());
        existingAppointment.setDoctorName(updatedAppointment.getDoctorName());
        existingAppointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
        existingAppointment.setTimeSlot(updatedAppointment.getTimeSlot());
        existingAppointment.setConsultationMode(updatedAppointment.getConsultationMode());
        existingAppointment.setStatus(updatedAppointment.getStatus());
        
        return appointmentRepository.save(existingAppointment);
    }

    @Transactional
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }
}
