package com.hireoeasy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hireoeasy.domain.EmployeeEducation;
import com.hireoeasy.repository.EmployeeEducationRepository;

@Service
public class EmployeeEducationService {

	private EmployeeEducationRepository educationRepository;

	// to save the education
	public void save(EmployeeEducation education) {
		// job.setCreatedDate(new Date());
		educationRepository.save(education);
	}

	// To get list of education
	public List<EmployeeEducation> findEducationByUserDetailId(Long id) {
		return educationRepository.findEducationById(id);
	}

	public void delete(Long id) {
		educationRepository.deleteById(id);
	}

	public Optional<EmployeeEducation> findByid(Long id) {
		return educationRepository.findById(id);
	}

}
