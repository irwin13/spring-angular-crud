package com.irwin13.patient.web;

import com.irwin13.patient.web.model.PageableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientApiTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void shouldReturnAllPatient() {
        // DatabaseLoader populate 2 rows
        ResponseEntity<PageableResponse> response = this.testRestTemplate
                .getForEntity("http://localhost:" + port + "/patient/search", PageableResponse.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getContent().size()).isEqualTo(2);
    }

}
