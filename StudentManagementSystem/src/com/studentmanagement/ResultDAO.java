package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultDAO {
	public void generateResult(int studentId) {

        String sql = "SELECT s.name, " +
                     "COUNT(m.mark_id) AS subject_count, " +
                     "SUM(m.marks) AS total_marks, " +
                     "AVG(m.marks) AS average_marks " +
                     "FROM students s " +
                     "JOIN marks m " +
                     "ON s.student_id = m.student_id " +
                     "WHERE s.student_id = ? " +
                     "GROUP BY s.student_id, s.name";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String name = rs.getString("name");
                int subjectCount = rs.getInt("subject_count");
                int totalMarks = rs.getInt("total_marks");
                double averageMarks = rs.getDouble("average_marks");

                String result;

                if (averageMarks >= 40) {
                    result = "PASS";
                } else {
                    result = "FAIL";
                }

                System.out.println("\n===== STUDENT RESULT =====");
                System.out.println("Student Name : " + name);
                System.out.println("Subjects     : " + subjectCount);
                System.out.println("Total Marks  : " + totalMarks);
                System.out.printf("Average      : %.2f%n", averageMarks);
                System.out.println("Result       : " + result);

            } else {

                System.out.println("No marks found for this student!");
            }

            rs.close();
            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
