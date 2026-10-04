package com.employeeleavemanagement;

import java.sql.Date;
import java.util.Scanner;

public class EmployeeLeaveManagement {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

        EmployeeDAO employeeDAO = new EmployeeDAO();
        LeaveBalanceDAO balanceDAO = new LeaveBalanceDAO();
        LeaveRequestDAO leaveDAO = new LeaveRequestDAO();

        while (true) {

            System.out.println("\n===== EMPLOYEE LEAVE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Create Leave Balance");
            System.out.println("4. View Leave Balance");
            System.out.println("5. Apply Leave");
            System.out.println("6. View Leave Requests");
            System.out.println("7. Approve Leave");
            System.out.println("8. Reject Leave");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter employee name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter department: ");
                    String department = sc.nextLine();

                    Employee employee =
                            new Employee(name, email, department);

                    employeeDAO.addEmployee(employee);

                    break;


                case 2:

                    System.out.println("\n--- Employee List ---");

                    employeeDAO.viewEmployees();

                    break;


                case 3:

                    System.out.print("Enter employee ID: ");
                    int balanceEmployeeId = sc.nextInt();

                    balanceDAO.createBalance(balanceEmployeeId);

                    break;


                case 4:

                    System.out.print("Enter employee ID: ");
                    int viewEmployeeId = sc.nextInt();

                    balanceDAO.viewBalance(viewEmployeeId);

                    break;


                case 5:

                    System.out.print("Enter employee ID: ");
                    int employeeId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter leave type: ");
                    String leaveType = sc.nextLine();

                    System.out.print("Enter start date (YYYY-MM-DD): ");
                    Date startDate = Date.valueOf(sc.nextLine());

                    System.out.print("Enter end date (YYYY-MM-DD): ");
                    Date endDate = Date.valueOf(sc.nextLine());

                    System.out.print("Enter reason: ");
                    String reason = sc.nextLine();

                    LeaveRequest leaveRequest =
                            new LeaveRequest(
                                    employeeId,
                                    leaveType,
                                    startDate,
                                    endDate,
                                    reason,
                                    "PENDING"
                            );

                    leaveDAO.applyLeave(leaveRequest);

                    break;


                case 6:

                    System.out.println("\n--- Leave Requests ---");

                    leaveDAO.viewLeaveRequests();

                    break;


                case 7:

                    System.out.print("Enter request ID: ");
                    int approveId = sc.nextInt();

                    leaveDAO.approveLeave(approveId);

                    break;


                case 8:

                    System.out.print("Enter request ID: ");
                    int rejectId = sc.nextInt();

                    leaveDAO.rejectLeave(rejectId);

                    break;


                case 9:

                    System.out.println("Thank you for using Employee Leave Management System!");

                    sc.close();

                    System.exit(0);

                    break;


                default:

                    System.out.println("Invalid choice! Please try again.");
            }
        }
	  }
}
