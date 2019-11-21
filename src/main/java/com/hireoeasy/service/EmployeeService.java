package com.hireoeasy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hireoeasy.domain.Employee;
import com.hireoeasy.repository.EmployeeRepository;
import com.hireoeasy.utilities.PasswordUtil;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repo;
	
	private PasswordUtil passwordUtil;

	public void save(Employee employee) {
		passwordUtil = new PasswordUtil();
		employee.setPassword(passwordUtil.getSecurePassword(employee.getPassword()));
		repo.save(employee);
	}

	public List<Employee> listAll() {
		return repo.findAll();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Optional<Employee> findById(Long id) {
		return repo.findById(id);
	}
	
//	method to login. This first encrypts user inputed value,then compares with the value in database.and if success returns employee
	public Employee employeeLogin(Employee employee) {
		passwordUtil = new PasswordUtil();
		String email = employee.getEmail();
		String password = passwordUtil.getSecurePassword(employee.getPassword());
		return repo.employeeLogin(email, password);
	}

}
