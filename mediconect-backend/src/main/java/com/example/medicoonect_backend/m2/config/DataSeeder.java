package com.example.medicoonect_backend.m2.config;

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
        // Upcoming Virtual Appointment
        Appointment virtualUpcoming = new Appointment();
        virtualUpcoming.setPatientName("Priya Sharma");
        virtualUpcoming.setPatientPhone("+91 98765 43210");
        virtualUpcoming.setPatientEmail("priya.sharma@email.com");
        virtualUpcoming.setPatientAge(28);
        virtualUpcoming.setDoctorSpecialization("Cardiology");
        virtualUpcoming.setDoctorName("Dr. Rajesh Kumar");
        virtualUpcoming.setAppointmentDate(LocalDate.now().plusDays(2));
        virtualUpcoming.setTimeSlot(LocalTime.of(14, 30));
        virtualUpcoming.setConsultationMode(Appointment.ConsultationMode.VIRTUAL);
        virtualUpcoming.setStatus(Appointment.AppointmentStatus.UPCOMING);
        appointmentRepository.save(virtualUpcoming);

        // Upcoming In-Person Appointment
        Appointment inPersonUpcoming = new Appointment();
        inPersonUpcoming.setPatientName("Amit Patel");
        inPersonUpcoming.setPatientPhone("+91 91234 56789");
        inPersonUpcoming.setPatientEmail("amit.patel@email.com");
        inPersonUpcoming.setPatientAge(35);
        inPersonUpcoming.setDoctorSpecialization("Orthopedics");
        inPersonUpcoming.setDoctorName("Dr. Sunita Reddy");
        inPersonUpcoming.setAppointmentDate(LocalDate.now().plusDays(5));
        inPersonUpcoming.setTimeSlot(LocalTime.of(10, 0));
        inPersonUpcoming.setConsultationMode(Appointment.ConsultationMode.IN_PERSON);
        inPersonUpcoming.setStatus(Appointment.AppointmentStatus.UPCOMING);
        appointmentRepository.save(inPersonUpcoming);

        // Completed Virtual Appointment
        Appointment virtualCompleted = new Appointment();
        virtualCompleted.setPatientName("Kavita Singh");
        virtualCompleted.setPatientPhone("+91 99887 66554");
        virtualCompleted.setPatientEmail("kavita.singh@email.com");
        virtualCompleted.setPatientAge(42);
        virtualCompleted.setDoctorSpecialization("Dermatology");
        virtualCompleted.setDoctorName("Dr. Anil Verma");
        virtualCompleted.setAppointmentDate(LocalDate.now().minusDays(3));
        virtualCompleted.setTimeSlot(LocalTime.of(16, 0));
        virtualCompleted.setConsultationMode(Appointment.ConsultationMode.VIRTUAL);
        virtualCompleted.setStatus(Appointment.AppointmentStatus.COMPLETED);
        appointmentRepository.save(virtualCompleted);
    }
}
