package com.hireoeasy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hireoeasy.domain.UserAnswer;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer,Long> {

}
