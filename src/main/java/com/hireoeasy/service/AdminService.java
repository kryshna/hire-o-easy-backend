package com.hireoeasy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hireoeasy.domain.Admin;
import com.hireoeasy.domain.Employer;
import com.hireoeasy.repository.AdminRepository;
import com.hireoeasy.utilities.PasswordUtil;

@Service
public class AdminService {

	@Autowired
	private AdminRepository repo;

	private PasswordUtil passwordUtil;

	public void save(Admin admin) {
		passwordUtil = new PasswordUtil();
		System.out.println(passwordUtil.getSecurePassword(admin.getPassword()));
		admin.setPassword(passwordUtil.getSecurePassword(admin.getPassword()));
		repo.save(admin);
	}

	public List<Admin> listAll() {
		return repo.findAll();
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

	public Optional<Admin> findById(Long id) {
		return repo.findById(id);
	}
	
//	method to login. This first encrypts user inputed value,then compares with the value in database.and if success returns admin
	public Admin adminLogin(Admin admin) {
		passwordUtil = new PasswordUtil();
		String email = admin.getEmail();
		String password = passwordUtil.getSecurePassword(admin.getPassword());
		return repo.adminLogin(email, password);
	}

}
