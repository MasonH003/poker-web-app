<%--
  Created by IntelliJ IDEA.
  User: christophkp
  Date: 12/4/23
  Time: 3:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.pokerwebapp.model.entity.Account"%>
<%@ page import="com.example.pokerwebapp.model.entity.Block"%>
<%@ page import="com.example.pokerwebapp.model.entity.Friendship" %>
<%@ page import="com.example.pokerwebapp.controller.service.FriendshipService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.pokerwebapp.controller.service.BlockService" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="styles.css">

</head>
<body>
<% Account logged = (Account) session.getAttribute("Account");
    List<Friendship> incomingFriendships = FriendshipService.listIncomingFriendships(logged);
    List<Friendship> sent = FriendshipService.listSentFriendships(logged);
    List<Account> friends = FriendshipService.listFriends(logged);
    List<Block> blockList = BlockService.blockList(logged);
%>

<div class="d-flex">
<div class="friend-list">
    <h2>Friend Requests</h2>

        <ul class="list-group">
            <% for(Friendship f : incomingFriendships){ %>
            <li class="list-group-item">
                <span><%= f.getAccount().getUsername() %></span>
                <form method="post" action="acceptFriendRequestServlet">
                    <input type="hidden" name="f_accept_id" value="<%= f.getID() %>">
                    <button type="submit" class="btn btn-success">Accept</button>
                </form>
                <form method="post" action="removeFriendServlet">
                    <input type="hidden" name="friendship_id" value="<%= f.getID() %>">
                    <button type="submit" class="btn btn-danger">Decline</button>
                </form>
            </li>

            <% } %>
        </ul>
</div>


<div class="friend-list">
    <h2>Sent</h2>

    <ul class="list-group">
        <% for(Friendship f : sent){ %>
        <form method="post" action="removeFriendServlet">
        <li class="list-group-item">
            <span><%= f.getFriend().getUsername() %></span>
                <input type="hidden" name="friendship_id" value="<%= f.getID() %>">
                <button type="submit" class="btn btn-danger">Cancel</button>
        </li>
        </form>
        <% } %>
    </ul>
</div>

<div class="friend-list">
    <h2>Friends</h2>
    <ul class="list-group">
        <% for(Account a : friends){ %>
        <li class="list-group-item">
            <span><%= a.getUsername() %></span>
            <button type="button" class="btn btn-success">Invite</button>
            <% Friendship f = FriendshipService.dao.findFriendshipByAccounts(logged, a);%>
            <form method="post" action="removeFriendServlet">
            <input type="hidden" name="friendship_id" value="<%= f.getID() %>">
            <button type="submit" class="btn btn-danger">Remove</button>
            </form>

        </li>
        <% } %>
    </ul>
</div>

    <div class="friend-list">
        <h2>Blocked</h2>
        <ul class="list-group">
            <% for(Block b : blockList){ %>
            <li class="list-group-item">
                <span><%= b.getBlocked().getUsername() %></span>
                <form method="post" action="unblockServlet">
                    <input type="hidden" name="block_id" value="<%= b.getID() %>">
                    <button type="submit" class="btn btn-success">Unblock <i class="bi bi-person-fill-slash"></i></button>
            </li>
            <% } %>
        </ul>
    </div>




</div>








<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>