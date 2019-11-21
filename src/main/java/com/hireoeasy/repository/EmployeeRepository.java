package com.hireoeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hireoeasy.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//	for employee login. it takes employee from input and compare it with the encrypted value in database.if matched,login success,and returns employee
	@Query("SELECT e FROM Employee e WHERE e.email= :email AND password = :password")
	Employee employeeLogin(@Param("email") String email, @Param("password") String password);

}
