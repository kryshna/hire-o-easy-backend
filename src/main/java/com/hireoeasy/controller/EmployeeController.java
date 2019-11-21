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

import com.hireoeasy.domain.Employee;
import com.hireoeasy.service.EmployeeService;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeservice;

	// Get all employee list
	@GetMapping(value = "/webapi/employee/all")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> employeelist = employeeservice.listAll();
		return ResponseEntity.ok().body(employeelist);
	}

	// save new employee
	@PostMapping(value = "/webapi/employee/signp")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		employeeservice.save(employee);
		return ResponseEntity.ok().body("Employee added Success.");
	}

	// Delete employee
	@DeleteMapping(value = "/webapi/employee/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
		employeeservice.delete(id);
		return ResponseEntity.ok().body("Employee Deleted");
	}

	// Get a single employee for edit/show
	@GetMapping(value = "/webapi/employee/show/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
		Optional<Employee> employee = employeeservice.findById(id);
		if (employee.isPresent()) {
			Employee emp1 = employee.get();
			return ResponseEntity.ok().body(emp1);
		}
		return ResponseEntity.ok().body(null);
	}

//	EMployee login
	@PostMapping(value = "/webapi/employee/login")
	public ResponseEntity<Employee> employeeLogin(@RequestBody Employee employee) {
		Employee loggedInEmployee = employeeservice.employeeLogin(employee);
		return ResponseEntity.ok().body(loggedInEmployee);
	}

}
