package com.hireoeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hireoeasy.domain.EmployerContact;

@Repository
public interface EmployerContactRepository extends JpaRepository<EmployerContact, Long> {

}
