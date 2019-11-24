package com.hireoeasy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hireoeasy.domain.EmployeeEducation;
import com.hireoeasy.domain.EmployeeExperience;
import com.hireoeasy.repository.EmployeeExperienceRepository;

@Service
public class EmployeeExperienceService {

	@Autowired
	private EmployeeExperienceRepository experienceRepo;

	// to save the experience
	public void save(EmployeeExperience experience) {
		experienceRepo.save(experience);
	}

	public void delete(Long id) {
		experienceRepo.deleteById(id);
	}

	public Optional<EmployeeExperience> findByid(Long id) {
		return experienceRepo.findById(id);
	}

}
