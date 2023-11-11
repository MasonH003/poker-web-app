<%--
  Created by IntelliJ IDEA.
  User: Horacio Trujillo
  Date: 10/31/2023
  Time: 8:16 PM
  Webpage for the login page adapted from Prof. Hocha Parking Lot example
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

    <form method = "post" action = "loginServlet">
        <label for = "txt_login"> Login: </label>
        <input class= "form-control" type = "email" name = "txt_login" id = "txt_login" required />
        <label for = "txt_pass"> Password: </label>
        <input class = "form-control" type= "password" name = "txt_pass" id = "txt_pass" autocomplete = "off" required/>
    </form>

</body>
</html>
