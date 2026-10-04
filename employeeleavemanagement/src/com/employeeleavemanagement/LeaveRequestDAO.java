package com.employeeleavemanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LeaveRequestDAO {
	// Apply Leave
    public void applyLeave(LeaveRequest leaveRequest) {

        String checkSql = "SELECT total_leaves, leaves_taken FROM leave_balance WHERE employee_id = ?";

        String insertSql = "INSERT INTO leave_requests " +
                "(employee_id, leave_type, start_date, end_date, reason, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement checkPs = con.prepareStatement(checkSql)) {

            checkPs.setInt(1, leaveRequest.getEmployeeId());

            ResultSet rs = checkPs.executeQuery();

            if (!rs.next()) {
                System.out.println("Leave balance not found!");
                return;
            }

            int totalLeaves = rs.getInt("total_leaves");
            int leavesTaken = rs.getInt("leaves_taken");

            if (leavesTaken >= totalLeaves) {
                System.out.println("No leave balance available!");
                return;
            }

            try (PreparedStatement insertPs = con.prepareStatement(insertSql)) {

                insertPs.setInt(1, leaveRequest.getEmployeeId());
                insertPs.setString(2, leaveRequest.getLeaveType());
                insertPs.setDate(3, leaveRequest.getStartDate());
                insertPs.setDate(4, leaveRequest.getEndDate());
                insertPs.setString(5, leaveRequest.getReason());
                insertPs.setString(6, "PENDING");

                insertPs.executeUpdate();

                System.out.println("Leave applied successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Leave Requests
    public void viewLeaveRequests() {

        String sql = "SELECT * FROM leave_requests";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                System.out.println(
                    "Request ID: " + rs.getInt("request_id") +
                    ", Employee ID: " + rs.getInt("employee_id") +
                    ", Leave Type: " + rs.getString("leave_type") +
                    ", Start: " + rs.getDate("start_date") +
                    ", End: " + rs.getDate("end_date") +
                    ", Reason: " + rs.getString("reason") +
                    ", Status: " + rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 // Approve Leave
    public void approveLeave(int requestId) {

        String getSql = "SELECT employee_id, start_date, end_date, status FROM leave_requests WHERE request_id = ?";
        String updateRequestSql = "UPDATE leave_requests SET status = 'APPROVED' WHERE request_id = ?";
        String updateBalanceSql = "UPDATE leave_balance SET leaves_taken = leaves_taken + ? WHERE employee_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement getPs = con.prepareStatement(getSql)) {

            getPs.setInt(1, requestId);

            ResultSet rs = getPs.executeQuery();

            if (!rs.next()) {
                System.out.println("Leave request not found!");
                return;
            }

            int employeeId = rs.getInt("employee_id");
            String status = rs.getString("status");

            if (!status.equals("PENDING")) {
                System.out.println("Leave is already processed!");
                return;
            }

            long start = rs.getDate("start_date").getTime();
            long end = rs.getDate("end_date").getTime();

            int days = (int) ((end - start) / (1000 * 60 * 60 * 24)) + 1;

            try (PreparedStatement updateRequest =
                         con.prepareStatement(updateRequestSql);
                 PreparedStatement updateBalance =
                         con.prepareStatement(updateBalanceSql)) {

                updateRequest.setInt(1, requestId);
                updateRequest.executeUpdate();

                updateBalance.setInt(1, days);
                updateBalance.setInt(2, employeeId);
                updateBalance.executeUpdate();

                System.out.println("Leave approved successfully!");
                System.out.println("Leaves deducted: " + days);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Reject Leave
    public void rejectLeave(int requestId) {

        String sql = "UPDATE leave_requests SET status = 'REJECTED' " +
                     "WHERE request_id = ? AND status = 'PENDING'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, requestId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Leave rejected successfully!");
            } else {
                System.out.println("Leave request not found or already processed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
