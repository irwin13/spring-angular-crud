package com.irwin13.patient.web.repository;

import com.irwin13.patient.web.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p " +
            "WHERE LOWER(p.firstName) LIKE LOWER(?1) " +
            "OR LOWER(p.lastName) LIKE LOWER(?1)")
    List<Patient> findByName(String name);
}
