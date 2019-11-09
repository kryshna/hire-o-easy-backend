package com.hireoeasy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hireoeasy.domain.Job;
import com.hireoeasy.service.JobService;

@RestController
@CrossOrigin(origins = "*")
public class JobController {

	@Autowired
	private JobService jobService;

	// Get all Jobs List
	@GetMapping(value = "/webapi/jobs/all")
	public ResponseEntity<List<Job>> getAllJobs() {
		List<Job> joblist = jobService.listAll();
		return ResponseEntity.ok().body(joblist);
	}

	// save new job by employee
	@PostMapping(value = "/webapi/jobs/save")
	public ResponseEntity<?> postJob(@RequestBody Job job) {
		jobService.save(job);
		return ResponseEntity.ok().body("Job post Success.");
	}

}
