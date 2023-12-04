<%--
  Created by IntelliJ IDEA.
  User: christophkp
  Date: 12/4/23
  Time: 3:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.pokerwebapp.model.entity.Account"%>
<%@ page import="com.example.pokerwebapp.model.entity.Friendship" %>
<%@ page import="com.example.pokerwebapp.controller.service.FriendshipService" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"></head>
<body>
<% Account logged = (Account) session.getAttribute("Account");
    List<Friendship> incomingFriendships = FriendshipService.listIncomingFriendships(logged);
%>

<div class="container">
    <h2>Incoming</h2>
    <div class="row">

        <ul class="list-group">
            <% for(Friendship f : incomingFriendships){ %>
            <li class="list-group-item">
                <%= f.getAccount().getUsername() %>
            </li>
            <button type="button" class="btn btn-success">Accept</button>


            <% } %>
        </ul>

    </div>
</div>








<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>