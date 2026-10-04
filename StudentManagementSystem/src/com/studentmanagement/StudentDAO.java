package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
	public void addStudent(Student student) {

	    String sql = "INSERT INTO students (name, email, phone, department) VALUES (?, ?, ?, ?)";

	    try {
	        Connection connection = DBConnection.getConnection();

	        PreparedStatement ps = connection.prepareStatement(sql);

	        ps.setString(1, student.getName());
	        ps.setString(2, student.getEmail());
	        ps.setString(3, student.getPhone());
	        ps.setString(4, student.getDepartment());

	        int rows = ps.executeUpdate();

	        if (rows > 0) {
	            System.out.println("Student added successfully!");
	        }

	        ps.close();
	        connection.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void viewStudents() {

	    String sql = "SELECT * FROM students";

	    try {
	        Connection connection = DBConnection.getConnection();

	        PreparedStatement ps = connection.prepareStatement(sql);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            int studentId = rs.getInt("student_id");
	            String name = rs.getString("name");
	            String email = rs.getString("email");
	            String phone = rs.getString("phone");
	            String department = rs.getString("department");

	            System.out.println("-----------------------------");
	            System.out.println("Student ID : " + studentId);
	            System.out.println("Name       : " + name);
	            System.out.println("Email      : " + email);
	            System.out.println("Phone      : " + phone);
	            System.out.println("Department : " + department);
	        }

	        rs.close();
	        ps.close();
	        connection.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void updateStudent(Student student) {

	    String sql = "UPDATE students SET name = ?, email = ?, phone = ?, department = ? WHERE student_id = ?";

	    try {
	        Connection connection = DBConnection.getConnection();

	        PreparedStatement ps = connection.prepareStatement(sql);

	        ps.setString(1, student.getName());
	        ps.setString(2, student.getEmail());
	        ps.setString(3, student.getPhone());
	        ps.setString(4, student.getDepartment());
	        ps.setInt(5, student.getStudentId());

	        int rows = ps.executeUpdate();

	        if (rows > 0) {
	            System.out.println("Student updated successfully!");
	        } else {
	            System.out.println("Student ID not found!");
	        }

	        ps.close();
	        connection.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void deleteStudent(int studentId) {

	    String sql = "DELETE FROM students WHERE student_id = ?";

	    try {
	        Connection connection = DBConnection.getConnection();

	        PreparedStatement ps = connection.prepareStatement(sql);

	        ps.setInt(1, studentId);

	        int rows = ps.executeUpdate();

	        if (rows > 0) {
	            System.out.println("Student deleted successfully!");
	        } else {
	            System.out.println("Student ID not found!");
	        }

	        ps.close();
	        connection.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void searchStudent(int studentId) {

	    String sql = "SELECT * FROM students WHERE student_id = ?";

	    try {
	        Connection connection = DBConnection.getConnection();

	        PreparedStatement ps = connection.prepareStatement(sql);

	        ps.setInt(1, studentId);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            System.out.println("-----------------------------");
	            System.out.println("Student ID : " + rs.getInt("student_id"));
	            System.out.println("Name       : " + rs.getString("name"));
	            System.out.println("Email      : " + rs.getString("email"));
	            System.out.println("Phone      : " + rs.getString("phone"));
	            System.out.println("Department : " + rs.getString("department"));

	        } else {

	            System.out.println("Student ID not found!");
	        }

	        rs.close();
	        ps.close();
	        connection.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
