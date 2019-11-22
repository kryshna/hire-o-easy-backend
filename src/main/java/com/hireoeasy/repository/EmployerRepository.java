package com.hireoeasy.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hireoeasy.domain.Admin;
import com.hireoeasy.domain.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
	
//	for employer login. it takes employee from input and compare it with the encrypted value in database.if matched,login success,and returns employer
	@Query("SELECT e FROM Employer e WHERE e.email= :email AND password = :password")
	Employer employerLogin(@Param("email") String email, @Param("password") String password);
	
//	to select employee by industry type
	@Query("SELECT e FROM Employer e WHERE e.industryType= :industry_type ")
	List<Employer> findEmployeebyType(@Param("industry_type") String industry_type);
	
//	to select employee by status
	@Query("SELECT e FROM Employer e WHERE e.status= :status ")
	List<Employer> findEmployeebyStatus(@Param("status") String status);

//	to get count number of all employers
	@Query("SELECT COUNT(*) FROM Employer")
	int getAllEmployerCountNumber();
	
//	to get count number of employee by industry type
	@Query("SELECT COUNT(*) FROM Employer e WHERE e.industryType= :industry_type")
	int getEmployerCountByType(@Param("industry_type") String industry_type);
	
//	to change employer status
	@Transactional
	@Modifying
	@Query("UPDATE Employer e SET e.status=:newStatus WHERE e.id=:id")
	void changeEmployerStatus(@Param("id") Long id,@Param("newStatus") String newStatus);

}
