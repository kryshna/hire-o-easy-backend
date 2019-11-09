package com.hireoeasy.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hireoeasy.domain.Job;
import com.hireoeasy.repository.JobRepository;

@Service
public class JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
//	to save the job
	public void save(Job job) {
//		job.setCreatedDate(new Date());
		jobRepository.save(job);
	}
	
	
//	To get list of all jobs
	public List<Job> listAll() {
		return jobRepository.findAll();
	}
	

}
