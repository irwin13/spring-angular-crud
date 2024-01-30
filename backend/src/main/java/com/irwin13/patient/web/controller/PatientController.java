package com.irwin13.patient.web.controller;

import com.irwin13.patient.web.entity.Patient;
import com.irwin13.patient.web.repository.PatientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/search")
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

}
