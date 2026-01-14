package com.mycompany.clubsphere.resources;

import com.mycompany.clubsphere.dao.EventDAO;
import com.mycompany.clubsphere.model.Event;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@WebServlet("/manageEvents")
public class ManageEventsServlet extends HttpServlet {
    private EventDAO eventDAO = new EventDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                String clubIdParam = req.getParameter("clubId");
                int clubId = (clubIdParam != null && !clubIdParam.isEmpty())
                        ? Integer.parseInt(clubIdParam) : 0;

                String name = req.getParameter("newEvent");
                String description = req.getParameter("description");
                String location = req.getParameter("location");

                Date date = null;
                String dateParam = req.getParameter("date");
                if (dateParam != null && !dateParam.isEmpty()) {
                    date = Date.valueOf(dateParam);
                }

                Time time = null;
                String timeParam = req.getParameter("time");
                if (timeParam != null && !timeParam.isEmpty()) {
                    time = Time.valueOf(timeParam + ":00");
                }

                eventDAO.addEvent(clubId, name, date, time, location, description);

            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("eventId"));

                String clubIdParam = req.getParameter("clubId");
                int clubId = (clubIdParam != null && !clubIdParam.isEmpty())
                        ? Integer.parseInt(clubIdParam) : 0;

                String name = req.getParameter("editedName");
                String description = req.getParameter("editedDescription");
                String location = req.getParameter("editedLocation");

                Date date = null;
                String dateParam = req.getParameter("editedDate");
                if (dateParam != null && !dateParam.isEmpty()) {
                    date = Date.valueOf(dateParam);
                }

                Time time = null;
                String timeParam = req.getParameter("editedTime");
                if (timeParam != null && !timeParam.isEmpty()) {
                    time = Time.valueOf(timeParam + ":00");
                }

                eventDAO.updateEvent(id, clubId, name, date, time, location, description);

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("eventId"));
                eventDAO.deleteEvent(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/manageEvents");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Event> events = eventDAO.getAllEvents();
        req.setAttribute("events", events);
        req.getRequestDispatcher("/jsp/admin/manageEvents.jsp").forward(req, resp);
    }
}
