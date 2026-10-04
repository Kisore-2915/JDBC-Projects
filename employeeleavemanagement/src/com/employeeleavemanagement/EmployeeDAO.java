package com.employeeleavemanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO {
	
	// Add Employee
    public void addEmployee(Employee employee) {

        String sql = "INSERT INTO employees (employee_name, email, department) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employee.getEmployeeName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getDepartment());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee added successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Employees
    public void viewEmployees() {

        String sql = "SELECT * FROM employees";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                System.out.println(
                    "ID: " + rs.getInt("employee_id") +
                    ", Name: " + rs.getString("employee_name") +
                    ", Email: " + rs.getString("email") +
                    ", Department: " + rs.getString("department")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
