package com.studentmanagement;

import java.sql.Date;
import java.util.Scanner;

public class StudentManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        MarksDAO marksDAO = new MarksDAO();
        ResultDAO resultDAO = new ResultDAO();

        while (true) {

        	System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
        	System.out.println("1. Add Student");
        	System.out.println("2. View Students");
        	System.out.println("3. Update Student");
        	System.out.println("4. Delete Student");
        	System.out.println("5. Search Student");
        	System.out.println("6. Add Course");
        	System.out.println("7. View Courses");
        	System.out.println("8. Enroll Student");
        	System.out.println("9. View Enrollments");
        	System.out.println("10. Add Attendance");
        	System.out.println("11. View Attendance");
        	System.out.println("12. Add Marks");
        	System.out.println("13. View Marks");
        	System.out.println("14. Generate Result");
        	System.out.println("15. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                	System.out.print("Enter student name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter phone: ");
                    String phone = sc.nextLine();

                    System.out.print("Enter department: ");
                    String department = sc.nextLine();

                    Student student = new Student(
                            name,
                            email,
                            phone,
                            department
                    );

                    studentDAO.addStudent(student);

                    break;

                case 2:
                	studentDAO.viewStudents();

                    break;

                case 3:
                	System.out.print("Enter student ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter new email: ");
                    String newEmail = sc.nextLine();

                    System.out.print("Enter new phone: ");
                    String newPhone = sc.nextLine();

                    System.out.print("Enter new department: ");
                    String newDepartment = sc.nextLine();

                    Student updatedStudent = new Student(
                            updateId,
                            newName,
                            newEmail,
                            newPhone,
                            newDepartment
                    );

                    studentDAO.updateStudent(updatedStudent);

                    break;

                case 4: 
                	System.out.print("Enter student ID to delete: ");
                    int deleteId = sc.nextInt();

                    studentDAO.deleteStudent(deleteId);

                    break;

                case 5:

                    System.out.print("Enter student ID to search: ");
                    int searchId = sc.nextInt();

                    studentDAO.searchStudent(searchId);

                    break;

                case 6: 
                	System.out.print("Enter course name: ");
                    String courseName = sc.nextLine();

                    System.out.print("Enter duration: ");
                    String duration = sc.nextLine();

                    System.out.print("Enter fee: ");
                    double fee = sc.nextDouble();

                    Course course = new Course(
                            courseName,
                            duration,
                            fee
                    );

                    courseDAO.addCourse(course);

                    break;

                case 7:
                    courseDAO.viewCourses();
                    break;

                case 8: 
                	System.out.print("Enter student ID: ");
                    int studentId = sc.nextInt();

                    System.out.print("Enter course ID: ");
                    int courseId = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter enrollment date (YYYY-MM-DD): ");
                    String dateInput = sc.nextLine();

                    Date enrollmentDate = Date.valueOf(dateInput);

                    Enrollment enrollment = new Enrollment(
                            studentId,
                            courseId,
                            enrollmentDate
                    );

                    enrollmentDAO.enrollStudent(enrollment);

                    break;

                case 9:
                	enrollmentDAO.viewEnrollments();
                    break;

                case 10:
                	System.out.print("Enter student ID: ");
                    int attendanceStudentId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter attendance date (YYYY-MM-DD): ");
                    String attendanceDateInput = sc.nextLine();

                    System.out.print("Enter status (Present/Absent): ");
                    String status = sc.nextLine();

                    Date attendanceDate = Date.valueOf(attendanceDateInput);

                    Attendance attendance = new Attendance(
                        attendanceStudentId,
                        attendanceDate,
                        status
                    );

                    attendanceDAO.addAttendance(attendance);
                    break;
                    
                case 11:
                    attendanceDAO.viewAttendance();
                    break;
                    
                case 12:
                    System.out.print("Enter student ID: ");
                    int marksStudentId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter subject: ");
                    String subject = sc.nextLine();

                    System.out.print("Enter marks: ");
                    int marksValue = sc.nextInt();
                    sc.nextLine();
                    
                    if (marksValue < 0 || marksValue > 100) {
                        System.out.println("Invalid marks! Marks must be between 0 and 100.");
                        break;
                    }

                    Marks marks = new Marks(
                        marksStudentId,
                        subject,
                        marksValue
                    );

                    marksDAO.addMarks(marks);
                    break;

                case 13:
                	System.out.print("Enter student ID: ");
                    int viewMarksStudentId = sc.nextInt();
                    sc.nextLine();

                    marksDAO.viewMarks(viewMarksStudentId);
                    break;
                  
                case 14:
                    System.out.print("Enter student ID: ");
                    int resultStudentId = sc.nextInt();
                    sc.nextLine();

                    resultDAO.generateResult(resultStudentId);
                    break;
                  
                case 15:
                    System.out.println("Thank you for using Student Management System!");
                    sc.close();
                    return;
                    
                default:

                    System.out.println(
                            "Invalid choice! Please try again."
                    );
            }
        }
    }
}