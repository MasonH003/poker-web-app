<%--
  Created by IntelliJ IDEA.
  User: karis
  Date: 11/12/2023
  Time: 5:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Table</title>
    <jsp:include page="styling.jsp"/>
</head>
<body>

<div>
    <form method = "post" action = "SearchTableServlet">
        <div class="form-group">
            <label for = "txt_reg_pw"> Search for Tables: </label>
            <input class = "form-control" type= "password" name = "txt_reg_pw" id ="txt_reg_pw" autocomplete = "off" required/>
        </div>
        <div class="row mb-2">
            <div class="offset-md-1 col-12 col-md-2 d-grid">
                <button type="submit" onclick="return checkRegisterForm()" class="blue-button"> Register</button>
            </div>
            <div class="col-12 col-md-2 d-grid">
                <button type="reset" class="blue-button" id="clear-button"> Clear</button>
            </div>
            <div class="col-12 col-md-2 d-grid">
                <a type="button" class="blue-button" href="index.jsp"> Return</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>
