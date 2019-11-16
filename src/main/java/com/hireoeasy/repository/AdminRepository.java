package com.hireoeasy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hireoeasy.domain.Admin;
import com.hireoeasy.domain.Employer;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
//	for admin login. it takes admin from input and compare it with the encrypted value in database.if matched,login success,and returns admin
	@Query("SELECT a FROM Admin a WHERE a.email= :email AND password = :password")
	Admin adminLogin(@Param("email") String email, @Param("password") String password);
}