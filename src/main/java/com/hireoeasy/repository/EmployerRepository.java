package com.hireoeasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hireoeasy.domain.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
	
//	to select employee by industry type
	@Query("SELECT e FROM Employer e WHERE e.industryType= :industry_type ")
	List<Employer> findEmployeebyType(@Param("industry_type") String industry_type);

//	to get count number of all employers
	@Query("SELECT COUNT(*) FROM Employer")
	int getAllEmployerCountNumber();
	
//	to get count number of employee by industry type
	@Query("SELECT COUNT(*) FROM Employer e WHERE e.industryType= :industry_type")
	int getEmployerCountByType(@Param("industry_type") String industry_type);

}
