package com.sms.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/sms","sa","");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
}
