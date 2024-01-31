package com.irwin13.patient.web.controller;

import com.irwin13.patient.web.entity.Patient;
import com.irwin13.patient.web.model.PageableResponse;
import com.irwin13.patient.web.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/search")
    public ResponseEntity<PageableResponse> search(@RequestParam(name = "start", defaultValue = "0") int start,
                                 @RequestParam(name = "size", defaultValue = "10") int size) {

        Page<Patient> page = patientService.getAll(start, size);

        PageableResponse<Patient> result = new PageableResponse();
        result.setContent(page.getContent());
        result.setTotalRecord(page.getTotalElements());
        result.setNext(page.hasNext());
        result.setPrevious(page.hasPrevious());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patient> insert(@RequestBody Patient patient) {
        Patient result = patientService.insert(patient);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{pid}")
    public ResponseEntity<HttpStatus> update(@PathVariable("pid") long pid, @RequestBody Patient patient) {
        patient.setPid(pid);
        patientService.update(patient);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("pid") long pid) {
        patientService.delete(pid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
