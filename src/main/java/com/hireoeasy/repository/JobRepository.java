package com.hireoeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hireoeasy.domain.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
