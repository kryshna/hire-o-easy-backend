package com.hireoeasy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.hireoeasy.utilities.DatabaseConnection;

@Service
public class JobAplicationService {

	private Connection con = new DatabaseConnection().getConnection();
	private PreparedStatement pstm = null;

	public void applyForJob(Long employerId, Long jobId) throws SQLException {

		String query = "INSERT INTO employee_job (employee_id,job-Id) VALUES (?,?)";
		try {
			pstm = con.prepareStatement(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pstm.setLong(1, employerId);
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		finally {
			pstm.close();
			con.close();
		}

	}
}