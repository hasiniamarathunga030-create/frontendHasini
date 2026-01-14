package com.mycompany.clubsphere.resources;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.mycompany.clubsphere.config.DatabaseConfig;

@WebServlet("/forgot-password")
public class forgotPasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        boolean emailFound = false;

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT email FROM users WHERE email = ?")) {
            
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    emailFound = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (emailFound) {
            HttpSession session = req.getSession();
            session.setAttribute("resetEmail", email);
            resp.sendRedirect(req.getContextPath() + "/jsp/auth/resetPassword.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/jsp/auth/forgotPassword.jsp?error=1");
        }
    }
}
