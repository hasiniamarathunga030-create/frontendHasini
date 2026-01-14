<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    HttpSession mySession = request.getSession(false);
    String email = (mySession != null) ? (String) mySession.getAttribute("email") : null;
    String role = (mySession != null) ? (String) mySession.getAttribute("role") : null;

    if (email == null || !"admin".equals(role)) {
        response.sendRedirect(request.getContextPath() + "/jsp/auth/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin Dashboard</title>
</head>

<body>

<%@ include file="../includes/header.jsp" %>
<jsp:include page="../includes/navbar.jsp" />

<div class="container py-5">

    <h2 class="section-title mb-4">Admin Dashboard</h2>

    <div class="row g-4">

        <div class="col-md-4">
            <a href="analytics.jsp" class="admin-card text-decoration-none">
                <div class="p-4 rounded admin-card-inner">
                    <h4 class="admin-card-title">Analytics</h4>
                    <p class="admin-card-text">View platform statistics, engagement, and activity reports.</p>
                </div>
            </a>
        </div>

        <div class="col-md-4">
            <a href="manageClubs.jsp" class="admin-card text-decoration-none">
                <div class="p-4 rounded admin-card-inner">
                    <h4 class="admin-card-title">Manage Clubs</h4>
                    <p class="admin-card-text">Add, edit, update, and remove university clubs.</p>
                </div>
            </a>
        </div>

        <div class="col-md-4">
            <a href="manageEvents.jsp" class="admin-card text-decoration-none">
                <div class="p-4 rounded admin-card-inner">
                    <h4 class="admin-card-title"> Manage Events</h4>
                    <p class="admin-card-text">Create, update, and manage campus events.</p>
                </div>
            </a>
        </div>

    </div>

</div>

<jsp:include page="../includes/footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/script.js"></script>

</body>
</html>
