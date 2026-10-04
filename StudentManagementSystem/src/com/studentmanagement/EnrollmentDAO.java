package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentDAO {
	public void enrollStudent(Enrollment enrollment) {

        String sql = "INSERT INTO enrollments (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";

        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setDate(3, enrollment.getEnrollmentDate());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student enrolled successfully!");
            }

            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
        
        public void viewEnrollments() {

            String sql = "SELECT e.enrollment_id, s.name, c.course_name, " +
                         "e.enrollment_date " +
                         "FROM enrollments e " +
                         "JOIN students s ON e.student_id = s.student_id " +
                         "JOIN courses c ON e.course_id = c.course_id";

            try {
                Connection connection = DBConnection.getConnection();

                PreparedStatement ps = connection.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    System.out.println("-----------------------------");
                    System.out.println("Enrollment ID : " + rs.getInt("enrollment_id"));
                    System.out.println("Student Name  : " + rs.getString("name"));
                    System.out.println("Course        : " + rs.getString("course_name"));
                    System.out.println("Date          : " + rs.getDate("enrollment_date"));
                }

                rs.close();
                ps.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
