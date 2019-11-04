package com.hireoeasy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hireoeasy.domain.Employer;
import com.hireoeasy.service.EmployerService;

@RestController
@CrossOrigin(origins = "*")
public class EmployerController {

	@Autowired
	private EmployerService employerService;

	// Get all Employer list
	@GetMapping(value = "/webapi/employerhome/employers")
	public ResponseEntity<List<Employer>> getAllEmployer() {
		List<Employer> employerlist = employerService.listAll();
		return ResponseEntity.ok().body(employerlist);
	}

	// save new Employer
	@PostMapping(value = "/webapi/employer/signup")
	public ResponseEntity<?> addEmployer(@RequestBody Employer employer) {
		employerService.save(employer);
		return ResponseEntity.ok().body("Employer added Success.");
	}

	// Delete Employer
	@DeleteMapping(value = "/webapi/employerhome/employer/delete/{id}")
	public ResponseEntity<?> deleteEmployer(@PathVariable("id") Long id) {
		employerService.delete(id);
		return ResponseEntity.ok().body("Employer Deleted");
	}

	// Get a single employer for edit
	@GetMapping(value = "/webapi/employerhome/employer/edit/{id}")
	public ResponseEntity<Employer> getEmployer(@PathVariable("id") Long id) {
		Optional<Employer> employer = employerService.findById(id);
		if (employer.isPresent()) {
			Employer employerDto = employer.get();
			return ResponseEntity.ok().body(employerDto);
		}
		return ResponseEntity.ok().body(null);
	}

}
