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

	// Delete Job.
	@DeleteMapping(value = "/webapi/jobs/delete/{id}")
	public ResponseEntity<?> deleteJob(@PathVariable("id") Long id) {
		jobService.delete(id);
		return ResponseEntity.ok().body("Job Deleted");
	}

	// Get a single Job for edit and show by Id
	@GetMapping(value = "/webapi/jobs/show/{id}")
	public ResponseEntity<Job> getJob(@PathVariable("id") Long id) {
		Optional<Job> job = jobService.findByid(id);
		if (job.isPresent()) {
			Job jobData = job.get();
			return ResponseEntity.ok().body(jobData);
		}
		return ResponseEntity.ok().body(null);
	}
	
	// Get all Jobs List By Category
		@GetMapping(value = "/webapi/jobs/category/{category}")
		public ResponseEntity<List<Job>> getJobsByCategory(@PathVariable("category") String category ) {
			List<Job> joblist = jobService.getJobsbyCategory(category);
			return ResponseEntity.ok().body(joblist);
		}
	
	
}
