<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.clubsphere.model.Event" %>
<%@ page import="com.mycompany.clubsphere.model.Club" %>
<%@ page import="com.mycompany.clubsphere.model.User" %>
<%@ page import="com.mycompany.clubsphere.model.ViewFeedback" %>

<jsp:include page="/jsp/includes/header.jsp" />
<jsp:include page="/jsp/includes/navbar.jsp" />

<div class="container py-4">
  <h2 class="section-title">Upcoming Events</h2>
  
  <ul class="list-group mt-3">
    <% 
       List<Event> events = (List<Event>) request.getAttribute("events");
       User user = (User) session.getAttribute("user");
       if (events != null && !events.isEmpty()) {
           for (Event event : events) { 
    %>
      <li class="list-group-item">
        <strong><%= event.getEventName() %></strong><br/>
        <small class="text-muted"><%= event.getDescription() %></small><br/>
        <small>Date: <%= event.getDate() %> | Time: <%= event.getTime() %> | Location: <%= event.getLocation() %></small>

        <% if (user != null) { %>
        <form action="<%=request.getContextPath()%>/submitFeedback" method="POST" class="mt-2">
          <input type="hidden" name="eventId" value="<%= event.getEventId() %>" />
          <input type="hidden" name="userId" value="<%= user.getId() %>" />

          <label for="rating">Rating:</label>
          <select name="rating" class="form-select">
            <option value="1">1 - Poor</option>
            <option value="2">2</option>
            <option value="3">3 - Average</option>
            <option value="4">4</option>
            <option value="5">5 - Excellent</option>
          </select>

          <textarea name="comment" class="form-control mt-2" placeholder="Enter feedback"></textarea>
          <button type="submit" class="btn btn-primary btn-sm mt-1">Submit Feedback</button>
        </form>
        <% } else { %>
          <p class="text-muted mt-2">Please <a href="<%=request.getContextPath()%>/jsp/auth/login.jsp">log in</a> to submit feedback.</p>
        <% } %>

        <%
           List<ViewFeedback> feedbacks = event.getFeedbacks();
           if (feedbacks != null && !feedbacks.isEmpty()) {
        %>
          <div class="mt-3">
            <h6>Feedback from participants:</h6>
            <ul class="list-group list-group-flush">
              <% for (ViewFeedback fb : feedbacks) { %>
                <li class="list-group-item">
                  <strong>Rating:</strong> <%= fb.getRating() %>/5<br/>
                  <small><%= fb.getComment() %></small><br/>
                  <small class="text-muted">By: <%= fb.getUsername() %> on <%= fb.getSubmittedAt() %></small>
                </li>
              <% } %>
            </ul>
          </div>
        <% } else { %>
          <p class="text-muted mt-2">No feedback yet for this event.</p>
        <% } %>
      </li>
    <% } } else { %>
      <li class="list-group-item">No events available</li>
    <% } %>
  </ul>

  <%
     List<Club> clubs = (List<Club>) request.getAttribute("clubs");
     if (clubs != null && !clubs.isEmpty()) {
  %>
    <h2 class="section-title mt-5">Available Clubs</h2>
    <ul class="list-group mt-3">
      <% for (Club club : clubs) { %>
        <li class="list-group-item">
          <strong><%= club.getClubName() %></strong><br/>
          <small class="text-muted"><%= club.getDescription() %></small>
        </li>
      <% } %>
    </ul>
  <% } else { %>
    <p class="text-muted mt-3">No clubs available at the moment.</p>
  <% } %>
</div>

<jsp:include page="/jsp/includes/footer.jsp" />
