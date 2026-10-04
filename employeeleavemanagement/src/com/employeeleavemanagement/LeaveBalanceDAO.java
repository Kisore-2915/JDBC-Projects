package com.employeeleavemanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LeaveBalanceDAO {
	// Create leave balance for an employee
    public void createBalance(int employeeId) {

        String sql = "INSERT INTO leave_balance (employee_id, total_leaves, leaves_taken) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.setInt(2, 12);
            ps.setInt(3, 0);

            ps.executeUpdate();

            System.out.println("Leave balance created successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View leave balance
    public void viewBalance(int employeeId) {

        String sql = "SELECT * FROM leave_balance WHERE employee_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int total = rs.getInt("total_leaves");
                int taken = rs.getInt("leaves_taken");

                System.out.println("Employee ID: " + employeeId);
                System.out.println("Total Leaves: " + total);
                System.out.println("Leaves Taken: " + taken);
                System.out.println("Leaves Remaining: " + (total - taken));

            } else {
                System.out.println("Leave balance not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
