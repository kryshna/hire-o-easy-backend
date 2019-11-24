package com.hireoeasy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hireoeasy.domain.Job;
import com.hireoeasy.utilities.DatabaseConnection;

@Service
public class JobAplicationService {

	@Autowired
	private JobService jobService;

	private Connection con = new DatabaseConnection().getConnection();
	private PreparedStatement pstm = null;

	public void applyForJob(Long employerId, Long jobId) throws SQLException {

		String query = "INSERT INTO employee_job (employee_id,job_id) VALUES (?,?)";
		try {
			pstm = con.prepareStatement(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pstm.setLong(1, employerId);
			pstm.setLong(2, jobId);
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			pstm.close();
			con.close();
		}
	}

	public ArrayList<Job> findJobByEmployee(Long employeeId) throws SQLException {
		String query = "Select job_id from employee_job where employee_id = ?";
		ArrayList<Job> jobs = new ArrayList<>();

		try {
			pstm = con.prepareStatement(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pstm.setLong(1, employeeId);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Optional<Job> job = jobService.findByid(rs.getLong("job_id"));
				if (job.isPresent()) {
					Job jobDto = job.get();
					jobs.add(jobDto);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			pstm.close();
			con.close();
		}

		return jobs;

	}
}