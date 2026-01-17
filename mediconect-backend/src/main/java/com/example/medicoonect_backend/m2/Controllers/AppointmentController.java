package com.example.medicoonect_backend.m2.Controllers;

import com.example.medicoonect_backend.m2.Services.AppointmentService;
import com.example.medicoonect_backend.m2.entity.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment createdAppointment = appointmentService.createAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/virtual")
    public ResponseEntity<List<Appointment>> getVirtualUpcomingAppointments() {
        List<Appointment> appointments = appointmentService.getVirtualUpcomingAppointments();
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Appointment> completeAppointment(@PathVariable Long id) {
        try {
            Appointment updatedAppointment = appointmentService.updateAppointmentStatus(
                id, 
                Appointment.AppointmentStatus.COMPLETED
            );
            return ResponseEntity.ok(updatedAppointment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
