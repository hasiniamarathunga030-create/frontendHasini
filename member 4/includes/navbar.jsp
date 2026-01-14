<%@ page contentType="text/html;charset=UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-transparent py-3">
  <div class="container">
    <a class="navbar-brand d-flex align-items-center" href="<%=request.getContextPath()%>/index.jsp">
      <span class="ms-2">ClubSphere</span>
    </a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="mainNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/eventList">Clubs</a></li>
        <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/eventList">Events</a></li>
        <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/index.jsp#about">About</a></li>
        <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/jsp/auth/login.jsp">Login</a></li>
        <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/jsp/auth/register.jsp">Register</a></li>
      </ul>
    </div>
  </div>
</nav>
