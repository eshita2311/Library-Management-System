/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import librarymanagementsystem.util.DBConnection;


/**
 *
 * @author H
 */
public class LibrarianDAO {
     public static boolean login(String username, String password) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM librarian WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
     public static boolean removeBook(int bookId) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM books WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, bookId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
public static ResultSet getAvailableBooks() {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT id, title, author FROM books WHERE status='AVAILABLE'";
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
public static boolean addBook(String title, String author) {
    try {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO books (title, author, status) VALUES (?, ?, 'AVAILABLE')";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, author);
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
public static boolean issueBook(int bookId, int studentId) {
    try {
        Connection con = DBConnection.getConnection();

        String sql = "UPDATE books SET status='ISSUED', issued_to=?, issue_date=CURDATE() " +
                     "WHERE id=? AND status='AVAILABLE'";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, studentId);
        ps.setInt(2, bookId);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
public static boolean returnBook(int bookId) {
    try {
        Connection con = DBConnection.getConnection();

        String sql = "UPDATE books SET status='AVAILABLE', issued_to=NULL, issue_date=NULL " +
                     "WHERE id=? AND status='ISSUED'";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, bookId);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
public static ResultSet getIssuedBooks() {
    try {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT id, title, author, issued_to, issue_date, status " +
                     "FROM books WHERE status = 'ISSUED'";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

}
