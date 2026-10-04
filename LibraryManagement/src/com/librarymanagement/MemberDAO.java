package com.librarymanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
	// Add Member
    public void addMember(Member member) {

        String sql = "INSERT INTO members (member_name, email) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, member.getMemberName());
            ps.setString(2, member.getEmail());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Member added successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Members
    public void viewMembers() {

        String sql = "SELECT * FROM members";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                System.out.println(
                    "Member ID: " + rs.getInt("member_id") +
                    ", Name: " + rs.getString("member_name") +
                    ", Email: " + rs.getString("email")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
