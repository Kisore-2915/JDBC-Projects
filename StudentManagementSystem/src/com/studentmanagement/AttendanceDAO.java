package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceDAO {
	 public void addAttendance(Attendance attendance) {

	        String sql = "INSERT INTO attendance " +
	                     "(student_id, attendance_date, status) " +
	                     "VALUES (?, ?, ?)";

	        try {

	            Connection connection = DBConnection.getConnection();

	            PreparedStatement ps =
	                    connection.prepareStatement(sql);

	            ps.setInt(1, attendance.getStudentId());
	            ps.setDate(2, attendance.getAttendanceDate());
	            ps.setString(3, attendance.getStatus());

	            int rows = ps.executeUpdate();

	            if (rows > 0) {
	                System.out.println("Attendance added successfully!");
	            }

	            ps.close();
	            connection.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public void viewAttendance() {

		    String sql = "SELECT a.attendance_id, s.name, " +
		                 "a.attendance_date, a.status " +
		                 "FROM attendance a " +
		                 "JOIN students s " +
		                 "ON a.student_id = s.student_id";

		    try {

		        Connection connection = DBConnection.getConnection();

		        PreparedStatement ps =
		                connection.prepareStatement(sql);

		        ResultSet rs = ps.executeQuery();

		        while (rs.next()) {

		            System.out.println("-----------------------------");
		            System.out.println("Attendance ID : "
		                    + rs.getInt("attendance_id"));

		            System.out.println("Student Name  : "
		                    + rs.getString("name"));

		            System.out.println("Date          : "
		                    + rs.getDate("attendance_date"));

		            System.out.println("Status        : "
		                    + rs.getString("status"));
		        }

		        rs.close();
		        ps.close();
		        connection.close();

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	 }
}
