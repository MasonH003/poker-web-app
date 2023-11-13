<%@ page import="com.example.pokerwebapp.model.entity.Account"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>
  <jsp:include page="styling.jsp"/>
  <style>
    .blue-button {
      width: 100px; /* Adjust the width as needed */
    }
  </style>
</head>

<body>
<% Account logged = (Account) session.getAttribute("Account");
  String username = "";
  if(logged!=null) username = logged.getEmail();
%>


<% if(logged==null ){ %>

<div class="container">
  <h1>Welcome to Futuretech Poker</h1>
    <center>
      <img src = "images/FuturetechLogo.jpg" width="125" height="100"/>
    </center>
</div>

<a href="play-poker" class="blue-button">PLAY</a>

<br>

<a href="login.jsp" class="blue-button">LOG IN</a>

<br>

<a href="registernewaccount.jsp" class="blue-button">REGISTER</a>

<% } else { %>

<%
  username = logged.getEmail();
%>

<div style="position: absolute; top: 0; right: 0; padding: 10px;">
  <%= "USER: " + username %>
</div>

<form action="addFriendServlet" method="post">
  <label for="friendname">Username:</label>
  <input type="text" id="friendname" name="friendname">
  <button type="submit" value="Submit" class="blue-button">Add</button>
</form>

<form action="removeFriendServlet" method="post">
  <label for="removename">Username:</label>
  <input type="text" id="removename" name="removename">
  <button type="submit" value="Submit" class="blue-button">Remove</button>
</form>

<form action="blockAccountServlet" method="post">
  <label for="blockname">Username:</label>
  <input type="text" id="blockname" name="blockname">
  <button type="submit" value="Submit" class="blue-button">Block</button>
</form>


<% } %>










</body>

</html>