package com.hireoeasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hireoeasy.domain.UserAnswer;
import com.hireoeasy.repository.UserAnswerRepository;

@Service
public class UserAnswerService {
	
	@Autowired
	private UserAnswerRepository answerRepository;
	
	public void save(UserAnswer answer) {
		
		answerRepository.save(answer);
	}

}
