package com.hireoeasy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hireoeasy.domain.EmployerContact;
import com.hireoeasy.repository.EmployerContactRepository;

@Service
public class EmployerContactService {
	@Autowired
	private EmployerContactRepository employerContactRepository;

	public void save(EmployerContact employerContact) {
		employerContactRepository.save(employerContact);
	}

}
