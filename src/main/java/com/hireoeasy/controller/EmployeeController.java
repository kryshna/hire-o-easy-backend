package com.hireoeasy.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hireoeasy.domain.Employee;

import com.hireoeasy.domain.UserDataInput;
import com.hireoeasy.domain.UserDetail;
import com.hireoeasy.domain.Employer;
import com.hireoeasy.domain.Job;
import com.hireoeasy.service.EmployeeService;
import com.hireoeasy.service.EmployerService;
import com.hireoeasy.service.JobAplicationService;
import com.hireoeasy.service.JobService;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeservice;

	@Autowired
	private JobService jobService;

	@Autowired
	private JobAplicationService jobApplicationService;
	@Autowired
	private EmployerService employerService;

	// Get all employee list
	@GetMapping(value = "/webapi/employee/all")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> employeelist = employeeservice.listAll();
		return ResponseEntity.ok().body(employeelist);
	}

	// save new employee
	@PostMapping(value = "/webapi/employee/signup")
	public ResponseEntity<?> addEmployee(@RequestBody UserDataInput u) {
		Employee e = new Employee();
		e.setFullName(u.getFullName());
		e.setEmail(u.getEmail());
		e.setUserName(u.getUserName());
		e.setPassword(u.getPassword());
		
		UserDetail ud = new UserDetail();
		ud.setActivities(u.getActivities());
		ud.setAddress(u.getAddress());
		ud.setDob(u.getDob());
		ud.setField(u.getField());
		ud.setGender(u.getGender());
		ud.setInterPersonalSkills(u.getInterPersonalSkills());
		ud.setJobObjective(u.getJobObjective());
		ud.setJobPriority(u.getJobPriority());
		ud.setMaritalStatus(u.getMaritalStatus());
		ud.setPhone(u.getPhone());
		ud.setNationality(u.getNationality());
		ud.setReligion(u.getReligion());
		
		e.setUserDetail(ud);
		employeeservice.save(e);
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

	// EMployee login
	@PostMapping(value = "/webapi/employee/login")
	public ResponseEntity<Employee> employeeLogin(@RequestBody Employee employee) {
		Employee loggedInEmployee = employeeservice.employeeLogin(employee);
		return ResponseEntity.ok().body(loggedInEmployee);
	}
	
////	to save user detail
//	@PostMapping(value="/webapi/employee/{empId}/userdetail/save")
//	public ResponseEntity<?> saveUserDetail(@PathVariable("empId") Long empId,@RequestBody UserDetail userDetail) {
//		Optional<Employee> emp1 = employeeservice.findById(empId);
//		if(emp1.isPresent()) {
//			Employee emp = emp1.get();
//			emp.setUserDetail(userDetail);
//			employeeservice.save(emp);
//		} else {
//			System.out.println("Employee not found");
//		}
//		return ResponseEntity.ok().body("User Detail saved");
//	}
	
//	get user detail by employee id
	@GetMapping(value="/webapi/employee/{empId}/userdetail")
	public ResponseEntity<UserDetail> getUserDetailForEmployee(@PathVariable("empId") Long empId) {
		Optional<Employee> emp1 = employeeservice.findById(empId);
		if(emp1.isPresent()) {
			Employee emp = emp1.get();
			UserDetail userDetail = emp.getUserDetail();
			return ResponseEntity.ok().body(userDetail);
		} else {
			return null;
		}
	}

	@PutMapping(value = "/webapi/employee/{employee_id}/job/{job_id}/apply")
	public ResponseEntity<?> applyJob(@PathVariable("employee_id") Long employeeId,
			@PathVariable("job_id") Long jobId) {
		Optional<Employee> employee = employeeservice.findById(employeeId);
		Optional<Job> job = jobService.findByid(jobId);
		ArrayList<Job> jobs = new ArrayList<>();
		// if (job.isPresent()) {
		// Job jobDto = job.get();
		// jobs.add(jobDto);
		// }
		//
		// if (employee.isPresent()) {
		// Employee emp1 = employee.get();
		//
		// emp1.setJob(jobs);
		// employeeservice.save(emp1);
		// }

		if (job.isPresent() && employee.isPresent()) {
			try {
				jobApplicationService.applyForJob(employeeId, jobId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ResponseEntity.ok().body("job added ");
	}

	@GetMapping(value = "/webapi/employee/{employee_id}/jobs")
	public ResponseEntity<?> findJobByEmployee(@PathVariable("employee_id") Long employeeId) {
		Optional<Employee> employee = employeeservice.findById(employeeId);

		ArrayList<Job> jobs = new ArrayList<>();

		if (employee.isPresent()) {
			try {
				jobs = jobApplicationService.findJobByEmployee(employeeId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ResponseEntity.ok().body(jobs);
	}

	@GetMapping(value = "/webapi/employer/{employer_id}/job/{job_id}/applications")
	public ResponseEntity<?> findEmployeeByJob(@PathVariable("employer_id") Long employeeId,
			@PathVariable("jobId") Long jobId) {
		Optional<Job> job = jobService.findByid(jobId);
		Optional<Employer> employer = employerService.findById(employeeId);

		ArrayList<Employee> employeeList = new ArrayList<>();

		if (employer.isPresent() && job.isPresent()) {
			try {
				employeeList = jobApplicationService.findEmployeeByJob(jobId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ResponseEntity.ok().body(employeeList);
	}

}
