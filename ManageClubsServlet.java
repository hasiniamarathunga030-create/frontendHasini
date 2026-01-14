package com.mycompany.clubsphere.resources;

import com.mycompany.clubsphere.dao.ClubDAO;
import com.mycompany.clubsphere.model.Club;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/manageClubs")
public class ManageClubsServlet extends HttpServlet {
    private ClubDAO clubDAO = new ClubDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("add".equals(action)) {
            String name = req.getParameter("clubName");
            String description = req.getParameter("description");
            clubDAO.addClub(name, description);

        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("clubId"));
            String name = req.getParameter("clubName");
            String description = req.getParameter("description");
            clubDAO.updateClub(id, name, description);

        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("clubId"));
            clubDAO.deleteClub(id);
        }

        resp.sendRedirect(req.getContextPath() + "/manageClubs");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Club> clubs = clubDAO.getAllClubs();
        req.setAttribute("clubs", clubs);
        req.getRequestDispatcher("/jsp/admin/manageClubs.jsp").forward(req, resp);
    }
}
