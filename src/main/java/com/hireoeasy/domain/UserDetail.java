package com.hireoeasy.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "UserDetail")
@Table(name = "userdetail")
public class UserDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@OneToOne(mappedBy = "userDetail", fetch = FetchType.EAGER)
	@JsonIgnore
	private Employee employee;

	@Column(nullable = true)
	private Date dob;
	private String nationality;
	private String gender;
	private String maritalStatus;
	@Column(nullable = true)
	private String religion;
	private String phone;
	private String address;

	@OneToMany(mappedBy = "userDetail", cascade = CascadeType.ALL)
	private List<EmployeeExperience> employeeExperiences;

	private String jobPriority;
	private String jobObjective;

	@OneToMany(mappedBy = "userDetail", cascade = CascadeType.ALL)
	private List<EmployeeEducation> employeeEducation;

	private String activities;

	private String interPersonalSkills;

	private String field;

	private String skills;
	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<EmployeeExperience> getEmployeeExperiences() {
		return employeeExperiences;
	}

	public void setEmployeeExperiences(List<EmployeeExperience> employeeExperiences) {
		this.employeeExperiences = employeeExperiences;
	}

	public String getJobPriority() {
		return jobPriority;
	}

	public void setJobPriority(String jobPriority) {
		this.jobPriority = jobPriority;
	}

	public String getJobObjective() {
		return jobObjective;
	}

	public void setJobObjective(String jobObjective) {
		this.jobObjective = jobObjective;
	}

	public List<EmployeeEducation> getEmployeeEducation() {
		return employeeEducation;
	}

	public void setEmployeeEducation(List<EmployeeEducation> employeeEducation) {
		this.employeeEducation = employeeEducation;
	}

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

	public String getInterPersonalSkills() {
		return interPersonalSkills;
	}

	public void setInterPersonalSkills(String interPersonalSkills) {
		this.interPersonalSkills = interPersonalSkills;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}
