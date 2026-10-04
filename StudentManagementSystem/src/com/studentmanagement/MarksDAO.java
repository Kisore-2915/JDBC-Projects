package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarksDAO {
	public void addMarks(Marks mark) {

        String sql = "INSERT INTO marks " +
                     "(student_id, subject, marks) " +
                     "VALUES (?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setInt(1, mark.getStudentId());
            ps.setString(2, mark.getSubject());
            ps.setInt(3, mark.getMarks());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Marks added successfully!");
            }

            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void viewMarks(int studentId) {

	    String sql =  "SELECT m.mark_id, s.name, m.subject, m.marks " +
	                  "FROM marks m " +
	                  "JOIN students s " +
	                  "ON m.student_id = s.student_id " +
	                  "WHERE s.student_id = ?";

	    try {

	        Connection connection = DBConnection.getConnection();

	        PreparedStatement ps =
	                connection.prepareStatement(sql);
	        
	        ps.setInt(1, studentId);

	        ResultSet rs = ps.executeQuery();
	        
	        if (!rs.isBeforeFirst()) {
	            System.out.println("No marks found for this student!");
	        }

	        while (rs.next()) {

	            System.out.println("-----------------------------");

	            System.out.println("Mark ID     : "
	                    + rs.getInt("mark_id"));

	            System.out.println("Student Name: "
	                    + rs.getString("name"));

	            System.out.println("Subject     : "
	                    + rs.getString("subject"));

	            System.out.println("Marks       : "
	                    + rs.getInt("marks"));
	        }

	        rs.close();
	        ps.close();
	        connection.close();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
