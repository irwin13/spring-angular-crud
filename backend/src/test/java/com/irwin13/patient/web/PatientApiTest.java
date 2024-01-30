package com.irwin13.patient.web;

import static org.assertj.core.api.Assertions.assertThat;

import com.irwin13.patient.web.entity.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientApiTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void shouldReturnAllPatient() {
        // DatabaseLoader populate 2 rows
        assertThat(this.testRestTemplate
                .getForObject("http://localhost:" + port + "/patient/search", Patient[].class))
                .hasSize(2);
    }

    @Test
    void shouldContainPatientFooBar() {
        assertThat(this.testRestTemplate
                .getForObject("http://localhost:" + port + "/patient/search", Patient[].class))
                .filteredOn(patient -> patient.getFirstName().equals("Foo") && patient.getLastName().equals("Bar"))
                .isNotEmpty();
    }

}
