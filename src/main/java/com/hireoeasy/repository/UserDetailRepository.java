package com.hireoeasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hireoeasy.domain.Job;
import com.hireoeasy.domain.UserDetail;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
	
	@Query("SELECT j FROM Job j WHERE skill LIKE %:search_term% ")
	List<Job> searchCvByTitle(@Param("search_term") String search_term);

}
