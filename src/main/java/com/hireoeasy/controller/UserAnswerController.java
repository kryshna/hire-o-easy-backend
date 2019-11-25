package com.hireoeasy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hireoeasy.domain.EmployeeEducation;
import com.hireoeasy.domain.Exam;
import com.hireoeasy.domain.UserAnswer;
import com.hireoeasy.domain.UserDetail;
import com.hireoeasy.service.ExamService;
import com.hireoeasy.service.UserAnswerService;
import com.hireoeasy.service.UserDetailService;

@Controller
@CrossOrigin(origins = "*")
public class UserAnswerController {

	@Autowired
	private UserAnswerService answerService;
	@Autowired
	private ExamService examService;
	@Autowired
	private UserDetailService userDetailService;

	// save answer
	@PostMapping(value = "/webapi/employee/{employee_id}/jobapply/exam/{exam_id}/submitanswer")
	public ResponseEntity<?> postEducation(@RequestBody UserAnswer answer,
			@PathVariable("employee_id") Long employeeDetailId, @PathVariable("exam_id") Long id) {
		Optional<Exam> examDetail = examService.findById(id);
		Optional<UserDetail> userDetail = userDetailService.findByid(employeeDetailId);
		Exam examData;
		UserDetail userDetaildata;
		if (examDetail.isPresent() && userDetail.isPresent()) {
			examData = examDetail.get();
			userDetaildata = userDetail.get();

		} else {
			examData = null;
			userDetaildata = null;

		}
		answer.setUserId(userDetaildata);
		answer.setExampId(examData);
		answerService.save(answer);
		return ResponseEntity.ok().body("anser added Success.");
	}

}
