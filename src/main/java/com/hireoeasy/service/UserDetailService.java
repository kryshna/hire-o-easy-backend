package com.hireoeasy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hireoeasy.domain.Job;
import com.hireoeasy.domain.UserDetail;
import com.hireoeasy.repository.UserDetailRepository;

@Service
public class UserDetailService {
	
	@Autowired
	private UserDetailRepository userDetailRepository;
	
//	to save the UserDetail for cv
	public void save(UserDetail detail) {
		userDetailRepository.save(detail);
	}
	
	
	public List<UserDetail> listAll() {
		return userDetailRepository.findAll();
	}
	
	public void delete(Long id) {
		userDetailRepository.deleteById(id);
	}
	
	public Optional<UserDetail> findByid(Long id) {
		return userDetailRepository.findById(id);
	}

	
//	method to search job by title
	public List<Job> searchCvByTitle(String search_term) {
		return userDetailRepository.searchCvByTitle(search_term);
	}

}
