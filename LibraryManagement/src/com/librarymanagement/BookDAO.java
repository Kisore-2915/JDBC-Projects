package com.librarymanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDAO {
	// Add Book
    public void addBook(Book book) {

        String sql = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getQuantity());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Book added successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View All Books
    public void viewBooks() {

        String sql = "SELECT * FROM books";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                System.out.println(
                    "Book ID: " + rs.getInt("book_id") +
                    ", Title: " + rs.getString("title") +
                    ", Author: " + rs.getString("author") +
                    ", Quantity: " + rs.getInt("quantity")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Search Book by Title
    public void searchBook(String title) {

        String sql = "SELECT * FROM books WHERE title LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + title + "%");

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                    "Book ID: " + rs.getInt("book_id") +
                    ", Title: " + rs.getString("title") +
                    ", Author: " + rs.getString("author") +
                    ", Quantity: " + rs.getInt("quantity")
                );
            }

            if (!found) {
                System.out.println("Book not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
