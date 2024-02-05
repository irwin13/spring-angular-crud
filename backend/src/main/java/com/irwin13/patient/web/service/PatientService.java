package com.irwin13.patient.web.service;

import com.irwin13.patient.web.entity.Patient;
import com.irwin13.patient.web.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient insert(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient update(Patient patient) {
        if (patient.getPid() == null) {
            throw new RuntimeException("pid is null on update");
        }
        return patientRepository.save(patient);
    }

    public void delete(Long pid) {
        patientRepository.deleteById(pid);
    }

    public Page<Patient> getAll(int start, int size) {
        return patientRepository.findAll(PageRequest.of(start, size, Sort.by("pid")));
    }

    public Optional<Patient> findById(Long pid) {
        return patientRepository.findById(pid);
    }

    public List<Patient> findByName(String name) {
        if (!StringUtils.isEmpty(name)) {
            name = "%" + name + "%";
        }
        return patientRepository.findByName(name);
    }

}
