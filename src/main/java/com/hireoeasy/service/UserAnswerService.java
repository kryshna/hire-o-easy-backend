package com.hireoeasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hireoeasy.repository.UserAnswerRepository;

@Service
public class UserAnswerService {
	
	@Autowired
	private UserAnswerRepository answerRepository;

}
