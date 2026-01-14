package com.mycompany.clubsphere.resources;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.mycompany.clubsphere.config.DatabaseConfig;
import com.mycompany.clubsphere.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        session = req.getSession(true);

        String sql = "SELECT user_id, username, role FROM users WHERE email = ? AND password = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String role = rs.getString("role");

                    String normalizedRole = (role != null) ? role.trim().toLowerCase() : "";

                    User userObj = new User(id, username, normalizedRole);
                    userObj.setPassword(password);

                    session.setAttribute("user", userObj);

                    if ("admin".equals(normalizedRole)) {
                        resp.sendRedirect(req.getContextPath() + "/jsp/admin/dashboard.jsp");
                    } else if ("student".equals(normalizedRole)) {
                        resp.sendRedirect(req.getContextPath() + "/jsp/member/memberDashboard.jsp");
                    } else {
                        resp.sendRedirect(req.getContextPath() + "/jsp/auth/login.jsp?error=role");
                    }
                    return;
                }
            }

            resp.sendRedirect(req.getContextPath() + "/jsp/auth/login.jsp?error=1");

        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/jsp/auth/login.jsp?error=db");
        }
    }
}
