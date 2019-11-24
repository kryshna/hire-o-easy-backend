package com.hireoeasy.controller;

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

import com.hireoeasy.domain.UserDetail;
import com.hireoeasy.service.UserDetailService;

@Controller
public class UserDetailController {

	@Autowired
	private UserDetailService userDetailService;

	// Get all user detail list
	@GetMapping(value = "webapi/employee/details/findall")
	public ResponseEntity<List<UserDetail>> getAllUserDetail() {
		List<UserDetail> userDetaillist = userDetailService.listAll();
		return ResponseEntity.ok().body(userDetaillist);
	}

	// save user detail
	@PostMapping(value = "webapi/employee/details/add/{empId}")
	public ResponseEntity<?> addUserDetail(@RequestBody UserDetail userDetail) {
		userDetailService.save(userDetail);
		return ResponseEntity.ok().body("Employer added Success.");
	}

	// Delete userDetail
	@DeleteMapping(value = "webapi/employee/details/delete/{id}")
	public ResponseEntity<?> deleteUserDetail(@PathVariable("id") Long id) {
		userDetailService.delete(id);
		return ResponseEntity.ok().body("Employer Deleted");
	}

	// Get a single user detail for edit
	@GetMapping(value = "webapi/employee/details/edit/{id}")
	public ResponseEntity<UserDetail> getUserDetail(@PathVariable("id") Long id) {
		Optional<UserDetail> userDetail = userDetailService.findByid(id);
		if (userDetail.isPresent()) {
			UserDetail userDetailDto = userDetail.get();
			return ResponseEntity.ok().body(userDetailDto);
		}
		return ResponseEntity.ok().body(null);
	}

}
