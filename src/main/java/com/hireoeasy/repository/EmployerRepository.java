package com.hireoeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hireoeasy.domain.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

}
