<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../includes/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password - ClubSphere</title>
</head>

<body class="auth-page">

<jsp:include page="../includes/navbar.jsp" />

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card p-4 shadow-sm">

                <h3 class="mb-3 text-center">Forgot Password</h3>

                <p class="text-center text-muted mb-4">
                    Enter your email address and we’ll send you a reset link.
                </p>

                <% if (request.getParameter("error") != null) { %>
                    <div class="alert alert-danger text-center">
                        Email not found!
                    </div>
                <% } else if (request.getParameter("success") != null) { %>
                    <div class="alert alert-success text-center">
                        Password reset link sent successfully.
                    </div>
                <% } %>

                <form action="forgotPassword" method="post">
                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="email"
                               name="email"
                               class="form-control"
                               placeholder="you@nsbm.lk"
                               required>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">
                        Send Reset Link
                    </button>
                </form>

                <div class="text-center mt-3">
                    <small>
                        Remember your password?
                        <a href="<%=request.getContextPath()%>/jsp/auth/login.jsp">
                            Back to Login
                        </a>
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
