package com.myapp.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// import java.util.Date;

import com.myapp.myapp.dao.PatientRepository;
// import com.myapp.myapp.entities.Patient;

@SpringBootApplication
public class SpringmvcApplication implements CommandLineRunner   {
	
	@Autowired
	private PatientRepository patientRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringmvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// patientRepository.save(new Patient(null, "Auossa", new Date(), false, 5));
		patientRepository.findAll().forEach(p->{
			System.out.println(p.getName());
		});
	}



}

	


