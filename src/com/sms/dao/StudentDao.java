package com.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sms.beans.Student;
import com.sms.exceptions.DuplicateIdException;
import com.sms.exceptions.EmailIdException;
import com.sms.exceptions.NoSuchStudentException;
import com.sms.service.StudentService;
import com.sms.service.StudentServiceImpl;
import com.sms.validations.Validations;

public class StudentDao {

	static Connection con = DataBase.getConnection();
	static StudentService studentService = new StudentServiceImpl();

	public static void addStudent(int studentId) {

		Validations validations = new Validations();
		Student studentDetails = studentService.addStudent(studentId);
		boolean isDuplicate = validations.duplicateCheck(studentId);
		if (isDuplicate == true) {
			try {
				throw new DuplicateIdException(
						"Student Id " + studentId + " is already exists. Please enter a different Student ID");
			} catch (DuplicateIdException e) {
				e.printStackTrace();
			}
		}else if(!(studentDetails.getEmail().contains("@")) || !(studentDetails.getEmail().contains(".com"))) {
			try {
				throw new EmailIdException("Please enter a valid email id");
			} catch (EmailIdException e) {
				e.printStackTrace();
			}
		}else {

			try {
				String sql = "INSERT INTO STUDENT (studentId, name, email, country, contactno) VALUES (?, ?, ?, ?, ?)";

				PreparedStatement statement = con.prepareStatement(sql);
				statement.setInt(1, studentId);
				statement.setString(2, studentDetails.getName());
				statement.setString(3, studentDetails.getEmail());
				statement.setString(4, studentDetails.getCountry());
				statement.setString(5, studentDetails.getContactno());
				
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("A new student has been inserted successfully!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void updateStudent(int studentId) {
		String sql = "UPDATE STUDENT SET NAME = ?, EMAIL = ?, COUNTRY= ?, CONTACTNO = ? WHERE studentId = ?";
		Student studentDetails = studentService.updateStudent(studentId);
		Validations validations = new Validations();
		boolean isStudentExists = validations.duplicateCheck(studentId);
		if(isStudentExists==false) {
			try {
				throw new NoSuchStudentException("Student Id "+studentId+" does not exists in our records. Please enter valid student Id");
			} catch (NoSuchStudentException e) {
				e.printStackTrace();
			}
		}else if(!(studentDetails.getEmail().contains("@")) || !(studentDetails.getEmail().contains(".com"))) {
			try {
				throw new EmailIdException("Please enter a valid email id");
			} catch (EmailIdException e) {
				e.printStackTrace();
			}
		}else {
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, studentDetails.getName());
			statement.setString(2, studentDetails.getEmail());
			statement.setString(3, studentDetails.getCountry());
			statement.setString(4, studentDetails.getContactno());
			statement.setInt(5, studentId);
			int rowsUpdated = statement.executeUpdate();
			con.commit();
			if (rowsUpdated > 0) {
				System.out.println("An existing student has been updated successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	}

	public static void deleteStudent(int studentId) {

		String sql = "DELETE FROM STUDENT WHERE studentId=?";
		Validations validations = new Validations();
		boolean isStudentExists = validations.duplicateCheck(studentId);
		if(isStudentExists==false) {
			try {
				throw new NoSuchStudentException("Student Id "+studentId+" does not exists in our records. Please enter valid student Id");
			} catch (NoSuchStudentException e) {
				e.printStackTrace();
			}
		}else {

		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, studentId);
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("A student has been deleted successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	}

	public static void getAllStudents() {

		String sql = "SELECT * FROM STUDENT";

		try {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(sql);

			int count = 0;

			while (result.next()) {
				int studentId = result.getInt(1);
				String name = result.getString(2);
				String email = result.getString(3);
				String country = result.getString(4);
				String contactno = result.getString(5);

				String output = "Student #%d: %s - %s - %s - %s - %s ";
				System.out.println(String.format(output, ++count, studentId, name, email, country, contactno));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void getStudentById(int studentId) {

		String sql = "SELECT * FROM STUDENT WHERE studentId = ?";
		Validations validations = new Validations();
		boolean isStudentExists = validations.duplicateCheck(studentId);
		if(isStudentExists==false) {
			try {
				throw new NoSuchStudentException("Student Id "+studentId+" does not exists in our records. Please enter valid student Id");
			} catch (NoSuchStudentException e) {
				e.printStackTrace();
			}
		}else {
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, studentId);
			ResultSet result = ps.executeQuery();
			System.out.println(result);
			int count = 0;
			while (result.next()) {
				int roll = result.getInt(1);
				String name = result.getString(2);
				String email = result.getString(3);
				String country = result.getString(4);
				String contactno = result.getString(5);
				String output = "Student #%d: %s - %s - %s - %s - %s ";
				System.out.println(String.format(output, ++count, roll, name, email, country, contactno));
			}
		} catch (SQLException e) {
			System.out.println("In catch");
		}
	}
	}
}
