package com.irwin13.patient.web;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.irwin13.patient.web.controller.PatientController;
import com.irwin13.patient.web.entity.Patient;
import com.irwin13.patient.web.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;
import java.util.List;

@WebMvcTest(PatientController.class)
public class PatientServiceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PatientService patientService;

    @Test
    void shouldReturnAllPatient() throws Exception {
        List<Patient> list = new LinkedList<>();

        Patient patient1 = new Patient();
        patient1.setFirstName("First");
        list.add(patient1);

        Patient patient2 = new Patient();
        patient2.setFirstName("Second");
        list.add(patient2);

        Patient patient3 = new Patient();
        patient3.setFirstName("Third");
        list.add(patient3);

        Page mockPage = mock(Page.class);
        when(mockPage.getContent()).thenReturn(list);

        when(patientService.getAll(0, 10)).thenReturn(mockPage);

        this.mockMvc.perform(get("/patient/search"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(3)));

    }
}
