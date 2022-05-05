package com.myapp.myapp.web;

import java.util.List;
import java.util.Optional;

import com.myapp.myapp.dao.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myapp.myapp.entities.Patient;

@RestController
@RequestMapping("/api/patient")
public class PatientRestController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public ResponseEntity<List<Patient>> findAllPatient() {
        List<Patient> patientList = patientRepository.findAll();
        return ResponseEntity.ok(patientList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable(value = "id") Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            return ResponseEntity.ok().body(patient.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/new-patient")
    public Patient postPatient(@Validated @RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> addPatient(@RequestBody Patient patient){
        patientRepository.save(patient);
        HttpHeaders header = new HttpHeaders();
        header.add("desc", "Adding patient successfully");
        return ResponseEntity.status(HttpStatus.OK).headers(header).build();
        //return ResponseEntity.ok().build();
    }

    @GetMapping("/hello")
    public ResponseEntity<String> getHello() {
        String msg = "Welcome to my channel";
        HttpHeaders header = new HttpHeaders();
        header.add("desc", "Online book application");
        return new ResponseEntity<String>(msg, header, HttpStatus.OK);
    }
}
