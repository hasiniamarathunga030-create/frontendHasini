package com.mycompany.clubsphere.resources;

import com.mycompany.clubsphere.dao.FeedbackDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/saveFeedback")
public class FeedbackServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String userIdParam = req.getParameter("userId");
        String eventIdParam = req.getParameter("eventId");
        String ratingParam = req.getParameter("rating");
        String comment = req.getParameter("comment");

        boolean success = false;

        try {
            int userId = Integer.parseInt(userIdParam);
            int eventId = Integer.parseInt(eventIdParam);
            int rating = Integer.parseInt(ratingParam);

            success = FeedbackDAO.insertFeedback(userId, eventId, rating, comment);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (success) {
            resp.sendRedirect(req.getContextPath() + "/jsp/member/feedbackSuccess.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/jsp/member/submitFeedback.jsp?error=1");
        }
    }
}
