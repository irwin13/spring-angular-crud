package com.irwin13.patient.web.service;

import com.irwin13.patient.web.entity.Patient;
import com.irwin13.patient.web.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> search() {
        return patientRepository.findAll();
    }
}
