package com.mycompany.clubsphere.resources;

import com.mycompany.clubsphere.dao.EventDAO;
import com.mycompany.clubsphere.dao.ClubDAO;
import com.mycompany.clubsphere.dao.ViewFeedbackDAO;
import com.mycompany.clubsphere.model.Event;
import com.mycompany.clubsphere.model.Club;
import com.mycompany.clubsphere.model.ViewFeedback;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/eventList")
public class EventListServlet extends HttpServlet {
    private EventDAO eventDAO = new EventDAO();
    private ClubDAO clubDAO = new ClubDAO();
    private ViewFeedbackDAO feedbackDAO = new ViewFeedbackDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Event> events = eventDAO.getAllEvents();
        List<Club> clubs = clubDAO.getAllClubs();

        for (Event ev : events) {
            List<ViewFeedback> feedbacks = feedbackDAO.getFeedbackByEventId(ev.getEventId());
            ev.setFeedbacks(feedbacks);
        }

        req.setAttribute("events", events);
        req.setAttribute("clubs", clubs);

        req.getRequestDispatcher("/jsp/admin/eventList.jsp").forward(req, resp);
    }
}
