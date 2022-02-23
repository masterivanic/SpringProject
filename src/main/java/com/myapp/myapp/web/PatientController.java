package com.myapp.myapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.myapp.myapp.dao.PatientRepository;
import com.myapp.myapp.entities.Patient;

@Controller
public class PatientController {
	
	@Autowired
	private PatientRepository patientRepository;

	@GetMapping(path = "/")
	public String index() {
		return "index";
	}
	
	@GetMapping(path = "/patient")
	public String list(Model model , 
		@RequestParam(name="page" , defaultValue = "0")int page , 
		@RequestParam(name = "size" , defaultValue = "10") int size, 
		@RequestParam(name = "keyword" , defaultValue = "") String mc){

		Page<Patient> pagePatients = patientRepository.findByNameContains(mc, PageRequest.of(page, size));
		model.addAttribute("patients", pagePatients.getContent());
		model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("keyword", mc);
		return "patients";
	}

	@GetMapping(path = "/deletePatient")
	public String deletePatients(Model model, Long id,
	 	@RequestParam(name="page" , defaultValue = "0")int page){

		patientRepository.deleteById(id);
		System.out.println(patientRepository.findById(id));
		model.addAttribute("currentPage", page);
		return "redirect:/patient";
	}

	@GetMapping(path = "/create-patient")
	public String formPatient(Model model){
		model.addAttribute("patient", new Patient());
		model.addAttribute("mode", "new");
		return "formPatient";
	}

	@PostMapping(path = "/save-patient")
	@Transactional
	public String savePatient(@Valid Patient p, BindingResult bindingResult){
		System.out.println(p.getName() + "" + p.getDateNaissance() + "" + p.isMalade());
		if(bindingResult.hasErrors()) return "formPatient";
		patientRepository.save(p);
		return "confirmation/confirmation";
	}

	@GetMapping(path = "/editPatient")
	public String editPatient(Model model, Long id){
		// Patient p = patientRepository.getById(id);
		Patient p = patientRepository.findById(id).get();
		model.addAttribute("patient", p);
		model.addAttribute("mode", "edit");
		return "formPatient";
	}

	@GetMapping(path = "/jsonResponse")
	@ResponseBody
	public List<Patient> listInJson(){
		return patientRepository.findAll();
	}

	@GetMapping(path = "/jsonResponse/one/{id}")
	@ResponseBody
	public Patient oneItemInJson(@PathVariable Long id){
		return patientRepository.findById(id).get();
	}

}
