package com.irwin13.patient.web.controller;

import com.irwin13.patient.web.entity.Patient;
import com.irwin13.patient.web.model.PageableResponse;
import com.irwin13.patient.web.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/search")
    public PageableResponse getAll(@RequestParam(name = "start", defaultValue = "0") int start,
                                   @RequestParam(name = "size", defaultValue = "10") int size) {

        Page<Patient> page = patientService.getAll(start, size);

        PageableResponse<Patient> result = new PageableResponse();
        result.setContent(page.getContent());
        result.setTotalRecord(page.getTotalElements());
        result.setNext(page.hasNext());
        result.setPrevious(page.hasPrevious());

        return result;
    }

}
