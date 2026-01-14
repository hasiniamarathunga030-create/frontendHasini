<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ include file="../includes/header.jsp" %>
<jsp:include page="../includes/navbar.jsp" />

<%
    // Events list comes from servlet via request attribute
    List<com.mycompany.clubsphere.model.Event> events =
        (List<com.mycompany.clubsphere.model.Event>) request.getAttribute("events");
%>

<div class="container py-4">
    <h2 class="section-title">Manage Events</h2>

    <ul class="list-group mt-3">
        <% if (events != null && !events.isEmpty()) {
               for (com.mycompany.clubsphere.model.Event event : events) { %>
        <li class="list-group-item d-flex justify-content-between align-items-center">
            <span>
                <% if (request.getParameter("edit") != null
                       && Integer.parseInt(request.getParameter("edit")) == event.getEventId()) { %>
                    
                    <form method="post" action="<%=request.getContextPath()%>/manageEvents" class="d-inline-flex flex-wrap">
                        <input type="hidden" name="action" value="edit"/>
                        <input type="hidden" name="eventId" value="<%=event.getEventId()%>"/>
                        <input type="hidden" name="clubId" value="<%=event.getClubId()%>"/>

                        <input type="text" name="editedName"
                               value="<%=event.getEventName()%>"
                               class="form-control form-control-sm me-2 mb-2" required/>

                        <input type="text" name="editedDescription"
                               value="<%=event.getDescription()%>"
                               class="form-control form-control-sm me-2 mb-2" placeholder="Description"/>

                        <input type="text" name="editedLocation"
                               value="<%=event.getLocation()%>"
                               class="form-control form-control-sm me-2 mb-2" placeholder="Location"/>

                        <input type="date" name="editedDate"
                               value="<%=event.getDate()%>"
                               class="form-control form-control-sm me-2 mb-2" required/>

                        <input type="time" name="editedTime"
                               value="<%=event.getTime()%>"
                               class="form-control form-control-sm me-2 mb-2" required/>

                        <button class="btn btn-sm btn-success mb-2">Save</button>
                        <a href="<%=request.getContextPath()%>/manageEvents" class="btn btn-sm btn-secondary ms-1 mb-2">Cancel</a>
                    </form>
                <% } else { %>
                    <strong><%= event.getEventName() %></strong><br/>
                    <small class="text-muted"><%= event.getDescription() %></small><br/>
                    <small class="text-muted">📍 <%= event.getLocation() %> | 🗓 <%= event.getDate() %> | ⏰ <%= event.getTime() %></small>
                <% } %>
            </span>
            <span>
                <a href="<%=request.getContextPath()%>/manageEvents?edit=<%=event.getEventId()%>"
                   class="btn btn-sm btn-primary me-1">Edit</a>
                <form method="post" action="<%=request.getContextPath()%>/manageEvents" class="d-inline">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="eventId" value="<%=event.getEventId()%>"/>
                    <button type="submit" class="btn btn-sm btn-danger"
                            onclick="return confirm('Delete <%=event.getEventName()%>?');">Delete</button>
                </form>
            </span>
        </li>
        <%   }
           } else { %>
        <li class="list-group-item text-muted">No events available</li>
        <% } %>
    </ul>

    <div class="mt-4">
        <form method="post" action="<%=request.getContextPath()%>/manageEvents" class="d-flex flex-wrap">
            <input type="hidden" name="action" value="add"/>
            <input type="hidden" name="clubId" value="1"/> 

            <input type="text" name="newEvent" placeholder="New Event Name"
                   class="form-control me-2 mb-2" required/>

            <input type="text" name="description" placeholder="Description"
                   class="form-control me-2 mb-2"/>

            <input type="text" name="location" placeholder="Location"
                   class="form-control me-2 mb-2"/>

            <input type="date" name="date" class="form-control me-2 mb-2" required/>
            <input type="time" name="time" class="form-control me-2 mb-2" required/>

            <button class="btn btn-primary mb-2" type="submit">+ Add New Event</button>
        </form>
    </div>
</div>

<jsp:include page="../includes/footer.jsp" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/script.js"></script>
