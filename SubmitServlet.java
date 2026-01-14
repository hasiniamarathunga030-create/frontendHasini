package com.mycompany.clubsphere.servlet;

import com.mycompany.clubsphere.dao.SubmitFeedbackDAO;
import com.mycompany.clubsphere.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/submitFeedback")
public class SubmitFeedbackServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/auth/login.jsp");
            return;
        }

        try {
            int eventId = Integer.parseInt(request.getParameter("eventId"));
            int userId = user.getId();
            int rating = Integer.parseInt(request.getParameter("rating"));
            String comment = request.getParameter("comment");

            SubmitFeedbackDAO dao = new SubmitFeedbackDAO();
            dao.saveFeedback(eventId, userId, rating, comment);

            response.sendRedirect(request.getContextPath() + "/eventList");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to submit feedback");
        }
    }
}
