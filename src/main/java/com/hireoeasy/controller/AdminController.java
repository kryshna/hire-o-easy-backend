package com.hireoeasy.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.hireoeasy.domain.Admin;
import com.hireoeasy.service.AdminService;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {

	@Autowired
	private AdminService adminservice;

	// Get all admin list
	@GetMapping(value = "/webapi/adminhome/admins")
	public ResponseEntity<List<Admin>> getAllAdmin() {
		List<Admin> adminnlist = adminservice.listAll();
		return ResponseEntity.ok().body(adminnlist);
	}

	// save new admin
	@PostMapping(value = "/webapi/adminhome/addadmin")
	public ResponseEntity<?> addAdmin(@RequestBody Admin admin) {
		adminservice.save(admin);
		return ResponseEntity.ok().body("Admin added Success.");
	}

	// Delete admin
	@DeleteMapping(value = "/webapi/adminhome/admin/delete/{id}")
	public ResponseEntity<?> deleteAdmin(@PathVariable("id") Long id) {
		adminservice.delete(id);
		return ResponseEntity.ok().body("Admin Deleted");
	}

	// Get a single admin for edit
//	@PutMapping(value = "/webapi/adminhome/admin/edit/{id}")
//	public ResponseEntity<Admin> getAdmin(@PathVariable("id") Long id) {
//		Optional<Admin> admin = adminservice.findById(id);
//		if (admin.isPresent()) {
//			Admin admin1 = admin.get();
//			return ResponseEntity.ok().body(admin1);
//		}
//		return ResponseEntity.ok().body(null);
//	}

	@PutMapping("/webapi/adminhome/admin/edit/{id}")
	public ResponseEntity<Admin> updateEmployee(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Admin adminDetails) throws Exception {
		Admin admin = adminservice.findById(id)
				.orElseThrow(() -> new Exception("Employee not found for this id :: " + id));

		admin.setFullName(adminDetails.getFullName());
		admin.setEmail(adminDetails.getEmail());
		admin.setPassword(adminDetails.getPassword());
		final Admin updatedAdmin = adminservice.save(admin);
		return ResponseEntity.ok(updatedAdmin);
	}

}