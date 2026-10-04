package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDAO {
	public void addCourse(Course course) {

        String sql = "INSERT INTO courses (course_name, duration, fee) VALUES (?, ?, ?)";

        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getDuration());
            ps.setDouble(3, course.getFee());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Course added successfully!");
            }

            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void viewCourses() {

	    String sql = "SELECT * FROM courses";

	    try {
	        Connection connection = DBConnection.getConnection();

	        PreparedStatement ps = connection.prepareStatement(sql);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            System.out.println("-----------------------------");
	            System.out.println("Course ID   : " + rs.getInt("course_id"));
	            System.out.println("Course Name : " + rs.getString("course_name"));
	            System.out.println("Duration    : " + rs.getString("duration"));
	            System.out.println("Fee         : " + rs.getDouble("fee"));
	        }

	        rs.close();
	        ps.close();
	        connection.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
