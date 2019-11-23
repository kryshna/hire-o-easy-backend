package com.hireoeasy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.hireoeasy.domain.Exam;
import com.hireoeasy.repository.ExamRepository;

//import org.springframework.beans.factory.annotation.Autowired;

public class ExamService {
	
	@Autowired
	private ExamRepository repo;
	
	public List<Exam> findAll() {
		return repo.findAll();
	}
	
	public void save(Exam exam) {
		repo.save(exam);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public Optional<Exam> findById(Long id) {
		return repo.findById(id);
	}

}
