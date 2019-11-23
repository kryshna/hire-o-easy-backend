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
import com.hireoeasy.domain.Exam;
import com.hireoeasy.service.ExamService;

@RestController
@CrossOrigin(origins = "*")
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
	// Get all exam list
		@GetMapping(value = "/webapi/jobs/exam/all")
		public ResponseEntity<List<Exam>> getAllExam() {
			List<Exam> examlist = examService.findAll();
			return ResponseEntity.ok().body(examlist);
		}

	

}
