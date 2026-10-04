package com.librarymanagement;

import java.sql.Date;
import java.util.Scanner;

public class LibraryManagement {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        BookDAO bookDAO = new BookDAO();
        MemberDAO memberDAO = new MemberDAO();
        BookIssueDAO issueDAO = new BookIssueDAO();

        while (true) {

            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Add Member");
            System.out.println("5. View Members");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. View Issue Records");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter author: ");
                    String author = sc.nextLine();

                    System.out.print("Enter quantity: ");
                    int quantity = sc.nextInt();

                    Book book = new Book(title, author, quantity);

                    bookDAO.addBook(book);

                    break;


                case 2:

                    System.out.println("\n--- Book List ---");

                    bookDAO.viewBooks();

                    break;


                case 3:

                    System.out.print("Enter book title to search: ");
                    String searchTitle = sc.nextLine();

                    bookDAO.searchBook(searchTitle);

                    break;


                case 4:

                    System.out.print("Enter member name: ");
                    String memberName = sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    Member member = new Member(memberName, email);

                    memberDAO.addMember(member);

                    break;


                case 5:

                    System.out.println("\n--- Member List ---");

                    memberDAO.viewMembers();

                    break;


                case 6:

                    System.out.print("Enter book ID: ");
                    int bookId = sc.nextInt();

                    System.out.print("Enter member ID: ");
                    int memberId = sc.nextInt();

                    Date issueDate = new Date(System.currentTimeMillis());

                    BookIssue issue = new BookIssue(bookId,memberId,issueDate,null,"ISSUED");

                    issueDAO.issueBook(issue);

                    break;


                case 7:

                    System.out.print("Enter issue ID: ");
                    int issueId = sc.nextInt();

                    issueDAO.returnBook(issueId);

                    break;


                case 8:

                    System.out.println("\n--- Issue Records ---");

                    issueDAO.viewIssues();

                    break;


                case 9:

                    System.out.println("Thank you for using Library Management System!");

                    sc.close();
                    System.exit(0);

                    break;


                default:

                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
