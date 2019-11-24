package com.hireoeasy.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hireoeasy.domain.EmployeeEducation;

@Repository
public interface EmployeeEducationRepository extends JpaRepository<EmployeeEducation, Long>{
	
	@Query("SELECT e FROM EmployeeEducation  WHERE userDetailId = :userDetailId ")
	ArrayList<EmployeeEducation> findEducationById(@Param("userDetailId") Long id);

}
