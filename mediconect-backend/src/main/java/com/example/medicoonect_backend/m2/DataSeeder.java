package com.example.medicoonect_backend.m2;

import com.example.medicoonect_backend.m2.Repository.AppointmentRepository;
import com.example.medicoonect_backend.m2.entity.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final AppointmentRepository appointmentRepository;

    @Override
    public void run(String... args) {
        if (appointmentRepository.count() == 0) {
            seedAppointments();
        }
    }

    private void seedAppointments() {
        Appointment virtualAppointment = new Appointment();
        virtualAppointment.setPatientName("John Doe");
        virtualAppointment.setPatientPhone("+1 555-0123");
        virtualAppointment.setPatientEmail("john.doe@email.com");
        virtualAppointment.setPatientAge(35);
        virtualAppointment.setDoctorSpecialization("Cardiology");
        virtualAppointment.setDoctorName("Dr. Sarah Johnson");
        virtualAppointment.setAppointmentDate(LocalDate.now().plusDays(3));
        virtualAppointment.setTimeSlot(LocalTime.of(10, 30));
        virtualAppointment.setConsultationMode(Appointment.ConsultationMode.VIRTUAL);
        virtualAppointment.setStatus(Appointment.AppointmentStatus.UPCOMING);
        appointmentRepository.save(virtualAppointment);

        Appointment inPersonAppointment = new Appointment();
        inPersonAppointment.setPatientName("Jane Smith");
        inPersonAppointment.setPatientPhone("+1 555-0456");
        inPersonAppointment.setPatientEmail("jane.smith@email.com");
        inPersonAppointment.setPatientAge(28);
        inPersonAppointment.setDoctorSpecialization("Dermatology");
        inPersonAppointment.setDoctorName("Dr. Michael Brown");
        inPersonAppointment.setAppointmentDate(LocalDate.now().plusDays(5));
        inPersonAppointment.setTimeSlot(LocalTime.of(14, 0));
        inPersonAppointment.setConsultationMode(Appointment.ConsultationMode.IN_PERSON);
        inPersonAppointment.setStatus(Appointment.AppointmentStatus.UPCOMING);
        appointmentRepository.save(inPersonAppointment);
    }
}
