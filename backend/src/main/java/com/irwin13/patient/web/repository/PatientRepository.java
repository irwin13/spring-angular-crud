package com.irwin13.patient.web.repository;

import com.irwin13.patient.web.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {

}
