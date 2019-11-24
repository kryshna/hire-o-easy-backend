package com.hireoeasy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hireoeasy.domain.EmployeeEducation;
import com.hireoeasy.domain.Employer;
import com.hireoeasy.domain.Job;
import com.hireoeasy.domain.UserDetail;
import com.hireoeasy.service.EmployeeEducationService;
import com.hireoeasy.service.UserDetailService;

@Controller
public class EmployeeEdcationController {
	
	@Autowired
	private EmployeeEducationService educationService;
	@Autowired
	private UserDetailService userDetailService;
	
	// Get education
		@GetMapping(value = "/webapi/employee/employeedetail/education/{user_detail_id}")
		public ResponseEntity<List<EmployeeEducation>> getAllJobs(@PathVariable("user_detail_id") Long id) {
			Optional<UserDetail> userDetail = userDetailService.findByid(id);
			ArrayList<EmployeeEducation> educationList = new ArrayList<>();
			if(userDetail.isPresent()) {
				educationList = educationService.findEducationByUserDetailId(id);
			}
			return ResponseEntity.ok().body(educationList);
		}

		// save new education 
		@PostMapping(value = "/webapi/employee/employeedetail/education/{id}/save")
		public ResponseEntity<?> postEducation(@RequestBody EmployeeEducation employeeEducation,@PathVariable("id") Long id) {
			Optional<UserDetail> userDetail = userDetailService.findByid(id);
			UserDetail userDetailData;
			if(userDetail.isPresent()) {
				userDetailData = userDetail.get();
			} else {
				userDetailData = null;
			}
			employeeEducation.setUserDetail(userDetailData);
			educationService.save(employeeEducation);
			return ResponseEntity.ok().body("Employee education Success.");
		}

		// Delete education
		@DeleteMapping(value = "/webapi/employee/employeedetail/education/delete/{id}")
		public ResponseEntity<?> deleteEducation(@PathVariable("id") Long id) {
			educationService.delete(id);
			return ResponseEntity.ok().body("education Deleted");
		}

		// Get a single education for edit and show by Id
		@GetMapping(value = "/webapi/employee/employeedetail/education/show/{id}")
		public ResponseEntity<EmployeeEducation> getJob(@PathVariable("id") Long id) {
			Optional<EmployeeEducation> education = educationService.findByid(id);
			if (education.isPresent()) {
				EmployeeEducation employeeEducationData = education.get();
				return ResponseEntity.ok().body(employeeEducationData);
			}
			return ResponseEntity.ok().body(null);
		}


}
