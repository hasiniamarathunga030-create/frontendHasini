<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login - ClubSphere</title>
</head>
<body class="auth-page">
<jsp:include page="../includes/navbar.jsp" />

<div class="container py-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card p-4 shadow-sm">
        <h3 class="mb-3 text-center">Login</h3>

        <% if (request.getParameter("error") != null) { %>
          <div class="alert alert-danger text-center">Invalid email or password!</div>
        <% } else if (request.getParameter("logout") != null) { %>
          <div class="alert alert-success text-center">You have been logged out successfully.</div>
        <% } %>

        <form action="<%=request.getContextPath()%>/login" method="post">
          <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" name="email" class="form-control" placeholder="you@nsbm.lk" required>
          </div>
          <div class="mb-3">
            <div class="d-flex justify-content-between">
              <label class="form-label">Password</label>
              <a href="<%=request.getContextPath()%>/jsp/auth/forgotPassword.jsp" class="small">Forgot?</a>
            </div>
            <input type="password" name="password" class="form-control" required>
          </div>
          <button type="submit" class="btn btn-primary w-100">Login</button>
        </form>

        <div class="text-center mt-3">
          <small>Don’t have an account? 
            <a href="<%=request.getContextPath()%>/jsp/auth/register.jsp">Register</a>
          </small>
        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="../includes/footer.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/js/script.js"></script>
</body>
</html>
