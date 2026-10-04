package com.librarymanagement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookIssueDAO {
	// Issue Book
    public void issueBook(BookIssue issue) {

        String checkSql =
                "SELECT quantity FROM books WHERE book_id = ?";

        String insertSql =
                "INSERT INTO book_issues " +
                "(book_id, member_id, issue_date, status) " +
                "VALUES (?, ?, ?, 'ISSUED')";

        String updateSql =
                "UPDATE books SET quantity = quantity - 1 " +
                "WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement checkPs = con.prepareStatement(checkSql)) {

            checkPs.setInt(1, issue.getBookId());

            ResultSet rs = checkPs.executeQuery();

            if (!rs.next()) {
                System.out.println("Book not found!");
                return;
            }

            int quantity = rs.getInt("quantity");

            if (quantity <= 0) {
                System.out.println("Book is not available!");
                return;
            }

            try (PreparedStatement insertPs =
                         con.prepareStatement(insertSql);
                 PreparedStatement updatePs =
                         con.prepareStatement(updateSql)) {

                insertPs.setInt(1, issue.getBookId());
                insertPs.setInt(2, issue.getMemberId());
                insertPs.setDate(3, issue.getIssueDate());

                insertPs.executeUpdate();

                updatePs.setInt(1, issue.getBookId());
                updatePs.executeUpdate();

                System.out.println("Book issued successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Return Book
    public void returnBook(int issueId) {

        String getSql =
                "SELECT book_id, issue_date, status " +
                "FROM book_issues WHERE issue_id = ?";

        String updateIssueSql =
                "UPDATE book_issues SET return_date = ?, status = 'RETURNED' " +
                "WHERE issue_id = ?";

        String updateBookSql =
                "UPDATE books SET quantity = quantity + 1 " +
                "WHERE book_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement getPs = con.prepareStatement(getSql)) {

            getPs.setInt(1, issueId);

            ResultSet rs = getPs.executeQuery();

            if (!rs.next()) {
                System.out.println("Issue record not found!");
                return;
            }

            int bookId = rs.getInt("book_id");
            Date issueDate = rs.getDate("issue_date");
            String status = rs.getString("status");

            if (status.equals("RETURNED")) {
                System.out.println("Book is already returned!");
                return;
            }

            Date returnDate = new Date(System.currentTimeMillis());

            long difference =
                    returnDate.getTime() - issueDate.getTime();

            int days =
                    (int) (difference / (1000 * 60 * 60 * 24));

            int fine = 0;

            if (days > 7) {
                fine = (days - 7) * 10;
            }

            try (PreparedStatement updateIssuePs =
                         con.prepareStatement(updateIssueSql);
                 PreparedStatement updateBookPs =
                         con.prepareStatement(updateBookSql)) {

                updateIssuePs.setDate(1, returnDate);
                updateIssuePs.setInt(2, issueId);

                updateIssuePs.executeUpdate();

                updateBookPs.setInt(1, bookId);
                updateBookPs.executeUpdate();

                System.out.println("Book returned successfully!");
                System.out.println("Days borrowed: " + days);
                System.out.println("Fine: Rs." + fine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // View Issue Records
    public void viewIssues() {

        String sql = "SELECT * FROM book_issues";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                System.out.println(
                    "Issue ID: " + rs.getInt("issue_id") +
                    ", Book ID: " + rs.getInt("book_id") +
                    ", Member ID: " + rs.getInt("member_id") +
                    ", Issue Date: " + rs.getDate("issue_date") +
                    ", Return Date: " + rs.getDate("return_date") +
                    ", Status: " + rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
