<%--
  Created by IntelliJ IDEA.
  User: christophkp
  Date: 12/17/23
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.pokerwebapp.model.entity.Account"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
  <link rel="stylesheet" href="styles.css">

</head>
<body>
<% Account logged = (Account) session.getAttribute("Account");
%>
<center>
<div class="account-info">
  <h1>Username: <%= logged.getUsername()%> </h1>
  <h1> Wins:</h1>
  <h1> Losses:</h1>
  <h1> Balance: <%= logged.getMoney()%></h1>
</div>
</center>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
