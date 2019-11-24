package com.hireoeasy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hireoeasy.domain.EmployeeExperience;
import com.hireoeasy.domain.UserDetail;
import com.hireoeasy.service.EmployeeExperienceService;
import com.hireoeasy.service.UserDetailService;

@Controller
@CrossOrigin(origins = "*")
public class EmployeeExperienceController {

	@Autowired
	private EmployeeExperienceService experienceService;

	@Autowired
	private UserDetailService userDetailService;

	@GetMapping(value = "/webapi/employee/employeedetail/experience/{user_detail_id}")
	public ResponseEntity<List<EmployeeExperience>> getAllExperience(@PathVariable("user_detail_id") Long id) {
		Optional<UserDetail> userDetail = userDetailService.findByid(id);
		List<EmployeeExperience> experienceList = new ArrayList<>();
		if (userDetail.isPresent()) {
			experienceList = userDetail.get().getEmployeeExperiences();
		}
		return ResponseEntity.ok().body(experienceList);
	}

	// save new experience
	@PostMapping(value = "/webapi/employee/employeedetail/experience/{id}/save")
	public ResponseEntity<?> postExperience(@RequestBody EmployeeExperience employeeExperience,
			@PathVariable("id") Long id) {
		Optional<UserDetail> userDetail = userDetailService.findByid(id);
		UserDetail userDetailData;
		if (userDetail.isPresent()) {
			userDetailData = userDetail.get();
		} else {
			userDetailData = null;
		}
		employeeExperience.setUserDetail(userDetailData);
		experienceService.save(employeeExperience);
		return ResponseEntity.ok().body("Employee experience Success.");
	}

	// Delete experience
	@DeleteMapping(value = "/webapi/employee/employeedetail/experience/delete/{id}")
	public ResponseEntity<?> deleteExperince(@PathVariable("id") Long id) {
		experienceService.delete(id);
		return ResponseEntity.ok().body("education Deleted");
	}

	// Get a single experience for edit and show by Id
	@GetMapping(value = "/webapi/employee/employeedetail/experience/show/{id}")
	public ResponseEntity<EmployeeExperience> getJob(@PathVariable("id") Long id) {
		Optional<EmployeeExperience> experience = experienceService.findByid(id);
		if (experience.isPresent()) {
			EmployeeExperience employeeExperienceData = experience.get();
			return ResponseEntity.ok().body(employeeExperienceData);
		}
		return ResponseEntity.ok().body(null);
	}

}
