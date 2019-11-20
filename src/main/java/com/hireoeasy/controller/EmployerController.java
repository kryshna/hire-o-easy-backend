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

import com.hireoeasy.domain.Admin;
import com.hireoeasy.domain.Employer;
import com.hireoeasy.domain.EmployerDataInput;
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
	public ResponseEntity<?> addEmployer(@RequestBody EmployerDataInput employerData) {
		Employer employer = employerService.setEmployerDate(employerData);
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
	
//	To get list of employee by industry type
	@GetMapping(value = "/webapi/employerhome/employer/type/{industry_type}")
	public ResponseEntity<List<Employer>> getEmployerByType(@PathVariable("industry_type") String industry_type) {
		List<Employer> empListByType = employerService.getEmployerByType(industry_type);
		return ResponseEntity.ok().body(empListByType);
	}
	
//	To get number of all employers count
	@GetMapping(value = "/webapi/employerhome/employer/count")
	public ResponseEntity<Integer> getAllEmployerCount() {
		int empl_count = employerService.getAllEmployeeCountNumber();
		return ResponseEntity.ok().body(empl_count);
	}
	
//	To get number of employers count by type
	@GetMapping(value = "/webapi/employerhome/employer/count/type/{industry_type}")
	public ResponseEntity<Integer> getEmployerCountByType(@PathVariable("industry_type") String industry_type) {
		int empl_count = employerService.getAllEmployeeCountByType(industry_type);
		return ResponseEntity.ok().body(empl_count);
	}
	
//	Employer login
	@PostMapping(value="/webapi/employerhome/employer/login")
	public ResponseEntity<Employer> employerLogin(@RequestBody Employer employer) {
		Employer loggedInEmployer = employerService.employerLogin(employer);
		return ResponseEntity.ok().body(loggedInEmployer);
	}

}
