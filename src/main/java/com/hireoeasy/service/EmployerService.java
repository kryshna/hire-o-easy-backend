package com.hireoeasy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hireoeasy.domain.Employer;
import com.hireoeasy.domain.EmployerContact;
import com.hireoeasy.repository.EmployerRepository;
import com.hireoeasy.utilities.PasswordUtil;

@Service
public class EmployerService {

	@Autowired
	private EmployerRepository employerRepository;

	@Autowired
	private EmployerContactService contactService;

	private PasswordUtil passwordUtil;

//	public void save(Employer employer) {
//		System.out.println("EmployerService.save()");
//		System.out.println("Employer :: " + employer);
//		passwordUtil = new PasswordUtil();
////		EmployerContact employerContact = employer.getContact();
////
////		System.out.println("employerContact :: " + employerContact + "phone : " + employerContact.getPhone());
////		contactService.save(employerContact);
//		System.out.println(passwordUtil.getSecurePassword(employer.getPassword()));
//		employer.setPassword(passwordUtil.getSecurePassword(employer.getPassword()));
//		employerRepository.save(employer);
//	}

	public List<Employer> listAll() {
		return employerRepository.findAll();
	}

	public void delete(Long id) {
		employerRepository.deleteById(id);
	}

	public Optional<Employer> findById(Long id) {
		return employerRepository.findById(id);
	}
	
	
//	method to get list of employer by industry type.This takes argument induustry type in string
	public List<Employer> getEmployerByType(String industry_type) {
		return employerRepository.findEmployeebyType(industry_type);
	}
	
//	method to get  number (count of all employer)
	public int getAllEmployeeCountNumber() {
		return employerRepository.getAllEmployerCountNumber();
	}
	
//	method to get  number (count of all employer) by industry type.it takes industry type as parameter
	public int getAllEmployeeCountByType(String industry_type) {
		return employerRepository.getEmployerCountByType(industry_type);
	}

	public Employer save(Employer employer) {
		// TODO Auto-generated method stub
		System.out.println("EmployerService.save()");
		System.out.println("Employer :: " + employer);
		passwordUtil = new PasswordUtil();
		
		System.out.println(passwordUtil.getSecurePassword(employer.getPassword()));
		employer.setPassword(passwordUtil.getSecurePassword(employer.getPassword()));
		
		return employerRepository.save(employer);
	}

}