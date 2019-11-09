package com.hireoeasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hireoeasy.domain.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

//	To select jobs by category
	@Query("SELECT j FROM Job j WHERE j.category = :category")
	List<Job> getJobsByCategory(@Param("category") String category);
	
//	TO select list of jobs by employer who posted the job
//	@Query("SELECT j FROM Job WHERE j.employer = :employer_id ")
//	List<Job> getJobsByEmployer(@Param("employer_id") Long id );
	
//	TO search job by title
	@Query("SELECT j FROM Job j WHERE jobTitle LIKE %:search_term% ")
	List<Job> searchjobByTitle(@Param("search_term") String search_term);
}
