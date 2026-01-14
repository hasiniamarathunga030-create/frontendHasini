package com.mycompany.clubsphere.resources;

import com.mycompany.clubsphere.config.DatabaseConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName  = request.getParameter("lastName");
        String email     = request.getParameter("email");
        String password  = request.getParameter("password");

        try (Connection conn = DatabaseConfig.getDataSource().getConnection()) {

            String checkSql = "SELECT user_id FROM users WHERE email = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, email);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        request.setAttribute("error", "Email already registered!");
                        request.getRequestDispatcher("/jsp/auth/register.jsp").forward(request, response);
                        return;
                    }
                }
            }

            String insertSql = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, firstName + " " + lastName);
                insertStmt.setString(2, email);
                insertStmt.setString(3, password);
                insertStmt.setString(4, "student");
                insertStmt.executeUpdate();
            }

            request.setAttribute("success", "Registration successful for " + firstName + "!");
            request.getRequestDispatcher("/jsp/member/memberDashboard.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/jsp/auth/register.jsp").forward(request, response);
        }
    }
}
