package com.sms.validations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sms.dao.DataBase;

public class Validations {
	
	static Connection con = DataBase.getConnection();
	
	public boolean duplicateCheck(int studentId){
		
		String sql = "SELECT * FROM STUDENT WHERE STUDENTID = ?";

		PreparedStatement statement;
		try {
			statement = con.prepareStatement(sql);
			statement.setInt(1, studentId);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			return false;
		}
		
	}
}
