<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ include file="../includes/header.jsp" %>
<jsp:include page="../includes/navbar.jsp" />

<%
    List<com.mycompany.clubsphere.model.Club> clubs = 
        (List<com.mycompany.clubsphere.model.Club>) request.getAttribute("clubs");
%>

<div class="container py-4">
    <h2 class="section-title">Manage Clubs</h2>

    <ul class="list-group mt-3">
        <% if (clubs != null) {
               for (com.mycompany.clubsphere.model.Club club : clubs) { %>
        <li class="list-group-item d-flex justify-content-between align-items-center">
            <span>
                <% if (request.getParameter("edit") != null 
                      
                    <form method="post" action="<%=request.getContextPath()%>/manageClubs" class="d-inline-flex">
                        <input type="hidden" name="action" value="edit"/>
                        <input type="hidden" name="clubId" value="<%=club.getClubId()%>"/>
                        <input type="text" name="clubName" value="<%=club.getClubName()%>"
                               class="form-control form-control-sm me-2" required/>
                        <input type="text" name="description" value="<%=club.getDescription()%>"
                               class="form-control form-control-sm me-2" placeholder="Description"/>
                        <button type="submit" class="btn btn-sm btn-success">Save</button>
                        <a href="manageClubs.jsp" class="btn btn-sm btn-secondary ms-1">Cancel</a>
                    </form>
                <% } else { %>
                    <strong><%= club.getClubName() %></strong><br/>
                    <small class="text-muted"><%= club.getDescription() %></small>
                <% } %>
            </span>
            <span>
                <a href="manageClubs.jsp?edit=<%=club.getClubId()%>" 
                   class="btn btn-sm btn-primary me-1">Edit</a>
                <form method="post" action="<%=request.getContextPath()%>/manageClubs" class="d-inline">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="clubId" value="<%=club.getClubId()%>"/>
                    <button type="submit" class="btn btn-sm btn-danger"
                            onclick="return confirm('Delete <%=club.getClubName()%>?');">Delete</button>
                </form>
            </span>
        </li>
        <%   }
           } else { %>
        <li class="list-group-item text-muted">No clubs available</li>
        <% } %>
    </ul>

    <div class="mt-4">
        <form method="post" action="<%=request.getContextPath()%>/manageClubs" class="d-flex">
            <input type="hidden" name="action" value="add"/>
            <input type="text" name="clubName" placeholder="New Club Name"
                   class="form-control me-2" required/>
            <input type="text" name="description" placeholder="Description"
                   class="form-control me-2"/>
            <button class="btn btn-primary" type="submit">+ Add New Club</button>
        </form>
    </div>
</div>

<jsp:include page="../includes/footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/script.js"></script>
