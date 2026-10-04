package com.employeeleavemanagement;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	    private static final String URL = "jdbc:mysql://localhost:3306/employee_leave_db";
	    private static final String USERNAME = "root";
	    private static final String PASSWORD = "mypassword";

	    public static Connection getConnection() {

	        Connection con = null;

	        try {
	            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return con;
	    }
}
