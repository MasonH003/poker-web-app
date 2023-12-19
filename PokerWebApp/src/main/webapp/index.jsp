<%@ page import="com.example.pokerwebapp.model.entity.Account"%>
<%@ page import="com.example.pokerwebapp.model.entity.Friendship" %>
<%@ page import="com.example.pokerwebapp.controller.service.FriendshipService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%  String code = request.getParameter("msg");
  String message = "";

  if(code != null) {
    switch (code) {
      case "1":
        message = "You've already added or received a friend request from this user";
        break;
      case "2":
        message = "User not found";
        break;
    }
  }

%>

<!DOCTYPE html>
<html>

<head>
  <jsp:include page="components/styling.jsp"/>
  <jsp:include page="components/popup.jsp"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
  <link rel="stylesheet" href="styles.css">
  <style>
    .blue-button {
      width: 100px; /* Adjust the width as needed */
    }
  </style>
</head>

<body>
<% Account logged = (Account) session.getAttribute("Account");
String username;
List<Friendship> incomingFriendships = FriendshipService.listIncomingFriendships(logged);
%>




<% if(logged==null ){ %>

<div class="container">
  <center>
  <h1>Welcome to Futuretech Poker</h1>

      <img src = "images/FuturetechLogo.jpg" width="125" height="100"/>
    </center>
</div>

<!-- <a href="play-poker" class="blue-button">PLAY</a> -->

<br>
<center>
<a href="login.jsp" class="blue-button">LOG IN</a>
</center>
<br>
<center>
<a href="registernewaccount.jsp" class="blue-button">REGISTER</a>
</center>
<% } else { %>
<%
  username = logged.getEmail();
%>

<div class="dropdown" style="position: absolute; top: 0; right: 0; padding: 10px;">
  <button class="btn dropdown-toggle custom-color-dropdown-btn" type="button" data-bs-toggle="dropdown" aria-expanded="false">
    <i class="bi bi-person-circle"></i>
    <%= username %>
  </button>
  <ul class="dropdown-menu">
    <li><a class="dropdown-item" href="account.jsp">Profile <i class="bi bi-person-fill"></i></a></li>
    <li>
      <a href="#" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#addFriendModal">
        Add Friend <i class="bi bi-person-add"></i>
      </a>
    </li>
    <li><a class="dropdown-item" href="friends.jsp">Friends <i class="bi bi-person-heart"></i></a></li>

    <li>
      <a href="#" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#reportAccountModal">
        Report <i class="bi bi-exclamation-triangle"></i>
      </a>
    </li>

    <li>
      <a href="#" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#blockModal">
        Block <i class="bi bi-person-fill-slash"></i>
      </a>
    </li>

    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logoutServlet">Logout <i class="bi bi-box-arrow-left"></i></a></li>


  </ul>
</div>



<%--<button type="button" class="btn btn-success mt-3 ms-3" data-bs-toggle="modal" data-bs-target="#addFriendModal">--%>
<%--  Add Friend <i class="bi bi-person-add"></i>--%>
<%--</button>--%>
<%--<button type="button" class="btn btn-danger mt-3 ms-3" data-bs-toggle="modal" data-bs-target="#reportAccountModal">--%>
<%--  Report <i class="bi bi-exclamation-triangle"></i>--%>
<%--</button>--%>
<%-- Add Friend Modal--%>
<div class="modal fade" id="addFriendModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="addFriendModalLabel">Add A Friend!</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form method="post" action="addFriendServlet">
        <div class="modal-body">
          <div class="mb-3">
            <label for="friendName" class="form-label">Friend's Username</label>
            <input type="text" class="form-control" id="friendName" name="friend" placeholder="Enter friend's username">
          </div>
          <div class="text-end">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-primary">Send Friend Request</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
<%-- End Of Add Friend Modal --%>

<%-- Start of Report Account Modal --%>
<div class="modal fade" id="reportAccountModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="reportAccountModalLabel">Report!</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form method="post" action="reportAccountServlet">
        <div class="modal-body">
          <div class="mb-3">
            <label for="reportName" class="form-label">Account Username</label>
            <input type="text" class="form-control" id="reportName" name="report_name" placeholder="Enter account username">
          </div>
          <div class="mb-3">
            <label for="reportReason" class="form-label">Reason for Reporting</label>
            <textarea class="form-control" id="reportReason" name="report_message" placeholder="Enter reason for reporting"></textarea>
          </div>
          <div class="text-end">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-danger">Report Account</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>



<%-- Block Modal--%>
<div class="modal fade" id="blockModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="blockModalLabel">Block Account!</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form method="post" action="blockAccountServlet">
        <div class="modal-body">
          <div class="mb-3">
            <label for="blockName" class="form-label">Block Username</label>
            <input type="text" class="form-control" id="blockName" name="block" placeholder="Enter username to block">
          </div>
          <div class="text-end">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-danger">Block Account</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
<%-- End Of Block Modal --%>
<%--<img src = "images/FuturetechLogo.jpg" class="bottom-right-image"/>--%>



<img src = "images/Futuretech logo 3NEW.png" class="top-left-image"/>


<img src = "images/Futuretech logo 3NEW.png" class="bottom-right-image flipped"/>

<%--<a href="friends.jsp" class="blue-button">Show Friends</a>--%>




<% if(message.length()>0){ %>

<div class="alert custom-alert-size alert-danger d-flex align-items-center alert-dismissible fade show" role="alert">
  <i class="bi bi-exclamation-triangle"></i>
  <div>
    <%= message %>
  </div>
  <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
</div>

<% } /*Closing IF */ %>

<!-- pop-up for friends list -->

<%--<br>--%>

<%--<input type="checkbox" id="popup-trigger">--%>
<%--<label for="popup-trigger" id="popup-label" class="blue-button">Friends List</label>--%>

<%--<div id="popup-container">--%>
<%--  <span id="close-btn" onclick="closePopup()">X</span>--%>

<%--  <h2>Friends List</h2>--%>

<%--  <li>Friend 1</li>--%>
<%--  <li>Friend 2</li>--%>

<%--</div>--%>

<!------------------------------------------------------------------------------------->

<!-- Menu buttons -->
<div>
  <center>
  <a href="table.jsp" class = "blue-button">Join Table </a>
  </center>
</div>
<!------------------------------------------------------------------------------------->

<% } %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</body>

</html>