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
import com.hireoeasy.domain.EmployerContact;
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
	
//	To get list of employee by status
	@GetMapping(value = "/webapi/employerhome/employer/status/{status}")
	public ResponseEntity<List<Employer>> getEmployerByStatus(@PathVariable("status") String status) {
		List<Employer> empListByStatus = employerService.getEmployerByStatus(status);
		return ResponseEntity.ok().body(empListByStatus);
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
	
//	Get employer contact detail
	@GetMapping(value = "/webapi/employerhome/employer/contact/{id}")
	public ResponseEntity<EmployerContact> getEmployerContact(@PathVariable("id") Long id) {
		Optional<Employer> emp = employerService.findById(id);
		Employer employerData;
		if(emp.isPresent()) {
			employerData = emp.get();
			EmployerContact contact = employerData.getContact();
			return ResponseEntity.ok().body(contact);
		} else {
			employerData = null;
			return null;
		}
	}
	
//	method to change status of employer either pending or rejected or approved
	@GetMapping(value="/webapi/employerhome/employer/{id}/changestatus/{newStatus}")
	public ResponseEntity<?> changeEmployerStatus(@PathVariable("id") Long id,@PathVariable("newStatus") String newStatus) {
		employerService.changeStatus(id, newStatus);
		return ResponseEntity.ok().body("Status changed");
	}

}
