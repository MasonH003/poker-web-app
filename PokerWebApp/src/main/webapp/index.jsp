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
  <link rel="stylesheet" href="components/styles.css">
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
<button type="button" class="btn btn-success mt-3 ms-3" data-bs-toggle="modal" data-bs-target="#addFriendModal">
  Add Friend <i class="bi bi-person-add"></i>
</button>


<% for(Friendship f : incomingFriendships) { %>
<p> <%= f.getAccount().getUsername()%></p>
<form method="post" action="acceptFriendRequestServlet">
  <input type="hidden" name="friendship_ID" value="<%= f.getID()%>">
  <button type="submit" class="btn btn-success btn-sm">
    Accept
  </button>
</form>
<% } %>


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



<%-- Incoming Friend Requests --%>



<%-- Show the user currently logged in --%>
<%
  username = logged.getEmail();
%>

<div style="position: absolute; top: 0; right: 0; padding: 10px;">
  <%= "USER: " + username %>
</div>




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

<% } %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</body>

</html>