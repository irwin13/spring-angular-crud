package com.irwin13.patient.web.repository;

import com.irwin13.patient.web.entity.PatientAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientAddressRepository extends JpaRepository<PatientAddress, Long> {
}
