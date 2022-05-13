package com.sms.service;

import com.sms.beans.Student;

public class StudentServiceImpl implements StudentService{

	@Override
	public Student addStudent(int studentId) {
		Student student = new Student();
		student.setStudentId(studentId);
		student.setName("Nicholas");
		student.setEmail("nicholas@gmail.com");
		student.setCountry("USA");
		student.setContactno("1234567890");
		return student;
	}

	@Override
	public Student updateStudent(int studentId) {
		Student student = new Student();
		student.setStudentId(studentId);
		student.setName("Bravo");
		student.setEmail("bravo001@gmail.com");
		student.setCountry("India");
		student.setContactno("5487426321");
		return student;
		
	}
}
