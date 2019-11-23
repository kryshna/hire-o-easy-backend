package com.hireoeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hireoeasy.domain.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

}
