package com.irwin13.patient.web.controller;

import com.irwin13.patient.web.entity.Patient;
import com.irwin13.patient.web.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/search")
    public List<Patient> getAll() {
        return patientService.search();
    }

}
