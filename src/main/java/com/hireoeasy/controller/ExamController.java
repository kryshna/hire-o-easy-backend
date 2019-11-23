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
import com.hireoeasy.domain.Exam;
import com.hireoeasy.domain.Job;
import com.hireoeasy.service.EmployerService;
import com.hireoeasy.service.ExamService;
import com.hireoeasy.service.JobService;

@RestController
@CrossOrigin(origins = "*")
public class ExamController {

	@Autowired
	private ExamService examService;

	@Autowired
	private JobService jobService;

	// Get all exam list
	@GetMapping(value = "/webapi/jobs/exam/all")
	public ResponseEntity<List<Exam>> getAllExam() {
		List<Exam> examlist = examService.findAll();
		return ResponseEntity.ok().body(examlist);
	}

	// post job by job id
	@PostMapping(value = "/webapi/jobs/exam/{id}/save")
	public ResponseEntity<?> postJob(@RequestBody Exam exam, @PathVariable("id") Long id) {
		Optional<Job> job = jobService.findByid(id);
		Job jobData;
		if (job.isPresent()) {
			jobData = job.get();
		} else {
			jobData = null;
		}
		exam.setJob(jobData);
		examService.save(exam);
		return ResponseEntity.ok().body("Exam post Success.");
	}

	// Get all Exam List By Job
	@GetMapping(value = "/webapi/jobs/exam/{job_id}")
	public ResponseEntity<List<Exam>> getJobsByEmployer(@PathVariable("job_id") Long id) {
		Optional<Job> job = jobService.findByid(id);
		Job jobData;
		if (job.isPresent()) {
			jobData = job.get();
			List<Exam> examlist = jobData.getExam();
			return ResponseEntity.ok().body(examlist);
		} else {
			jobData = null;
			return null;
		}
	}
	
//	delete exam by Id
	@DeleteMapping(value="/webapi/jobs/exam/delete/{exam_id}")
	public ResponseEntity<?> deleteExam(@PathVariable("exam_id") Long id) {
		examService.delete(id);
		return ResponseEntity.ok().body("Exam Delete");
	}
	
}
