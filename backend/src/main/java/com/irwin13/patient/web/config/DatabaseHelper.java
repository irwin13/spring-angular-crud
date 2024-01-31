package com.irwin13.patient.web.config;

import com.irwin13.patient.web.entity.Patient;
import com.irwin13.patient.web.entity.PatientAddress;
import com.irwin13.patient.web.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

@Configuration
public class DatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

    @Bean
    CommandLineRunner populatePatientData(PatientRepository patientRepository) {

        return args -> {
            Patient patient1 = new Patient();
            patient1.setFirstName("John");
            patient1.setLastName("Doe");
            patient1.setDateOfBirth(Date.valueOf(LocalDate.of(1990, 9, 9)));
            patient1.setGender("male");

            Patient result1 = patientRepository.save(patient1);
            LOGGER.info("patient created = {}", result1);

            PatientAddress address1 = new PatientAddress();
            address1.setAddress("address1");
            address1.setPostCode(12345);
            address1.setPatient(result1);
            patient1.setPatientAddresses(Set.of(address1));
            patientRepository.save(patient1);

            Patient patient2 = new Patient();
            patient2.setFirstName("Foo");
            patient2.setLastName("Bar");
            patient2.setDateOfBirth(Date.valueOf(LocalDate.of(1970, 1, 1)));
            patient2.setGender("male");

            Patient result2 = patientRepository.save(patient2);
            LOGGER.info("patient created = {}", result2);

            PatientAddress address2 = new PatientAddress();
            address2.setAddress("address2");
            address2.setPostCode(67890);
            address2.setPatient(result2);
            patient2.setPatientAddresses(Set.of(address2));
            patientRepository.save(patient2);

        };
    }
}
