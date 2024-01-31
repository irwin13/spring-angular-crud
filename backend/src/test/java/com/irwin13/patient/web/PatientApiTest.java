package com.irwin13.patient.web;

import com.irwin13.patient.web.entity.Patient;
import com.irwin13.patient.web.model.PageableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientApiTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    String rootUrl;

    @BeforeEach
    void setRootUrl() {
        rootUrl = "http://localhost:" + port;
    }

    @Test
    void shouldReturnAll() {
        // DatabaseLoader populate 2 rows
        ResponseEntity<PageableResponse> response = this.testRestTemplate
                .getForEntity(rootUrl + "/patient/search", PageableResponse.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody().getContent().size()).isEqualTo(2);
        assertThat(response.getBody().getTotalRecord()).isEqualTo(2L);
    }

    @Test
    void shouldInsert() {
        Patient patient = new Patient();
        patient.setFirstName("first");
        ResponseEntity<Patient> response = this.testRestTemplate
                .postForEntity(rootUrl + "/patient", patient, Patient.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody().getPid()).isNotNull();
        assertThat(response.getBody().getFirstName()).isEqualTo("first");
    }

    @Test
    void shouldUpdate() {
        Patient patient = new Patient();
        patient.setFirstName("first");
        ResponseEntity<Patient> insertResponse = this.testRestTemplate
                .postForEntity(rootUrl + "/patient", patient, Patient.class);

        assertThat(insertResponse.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(insertResponse.getBody().getPid()).isNotNull();
        assertThat(insertResponse.getBody().getFirstName()).isEqualTo("first");

        Patient updatePatient = insertResponse.getBody();
        updatePatient.setFirstName("update first");

        this.testRestTemplate.put(rootUrl + "/patient/" + updatePatient.getPid(), updatePatient);

        ResponseEntity<PageableResponse> response = this.testRestTemplate
                .getForEntity(rootUrl + "/patient/search?pid=" + updatePatient.getPid(), PageableResponse.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody().getContent().size()).isEqualTo(1);
        assertThat(response.getBody().getTotalRecord()).isEqualTo(1L);
        assertThat(((Map<String, Object>) response.getBody().getContent().get(0))
                .get("firstName").toString())
                .isEqualTo("update first");
    }

    @Test
    void shouldDelete() {
        Patient patient = new Patient();
        patient.setFirstName("first");
        ResponseEntity<Patient> insertResponse = this.testRestTemplate
                .postForEntity(rootUrl + "/patient", patient, Patient.class);

        assertThat(insertResponse.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(insertResponse.getBody().getPid()).isNotNull();
        assertThat(insertResponse.getBody().getFirstName()).isEqualTo("first");

        long pid = insertResponse.getBody().getPid();
        this.testRestTemplate.delete(rootUrl + "/patient/" + pid);

        ResponseEntity<PageableResponse> response = this.testRestTemplate
                .getForEntity(rootUrl + "/patient/search?pid=" + pid, PageableResponse.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody().getContent().size()).isEqualTo(0);
        assertThat(response.getBody().getTotalRecord()).isEqualTo(0L);
    }

    @Test
    void shouldSearchByName() {

        ResponseEntity<PageableResponse> response = this.testRestTemplate
                .getForEntity(rootUrl + "/patient/search?name=foo", PageableResponse.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody().getContent().size()).isEqualTo(1);
        assertThat(response.getBody().getTotalRecord()).isEqualTo(1L);
        assertThat(((Map<String, Object>) response.getBody().getContent().get(0))
                .get("firstName").toString())
                .isEqualTo("Foo");
        assertThat(((Map<String, Object>) response.getBody().getContent().get(0))
                .get("lastName").toString())
                .isEqualTo("Bar");

    }
}