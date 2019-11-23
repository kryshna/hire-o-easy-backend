package com.hireoeasy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_answer")
public class UserAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private UserDetail userId;
	@OneToOne
	@JoinColumn(name = "examId", referencedColumnName = "id")
	private Exam exampId;
	private String answer;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserDetail getUserId() {
		return userId;
	}
	public void setUserId(UserDetail userId) {
		this.userId = userId;
	}
	public Exam getExampId() {
		return exampId;
	}
	public void setExampId(Exam exampId) {
		this.exampId = exampId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
