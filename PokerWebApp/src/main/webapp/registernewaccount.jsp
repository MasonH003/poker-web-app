<%--
  Created by IntelliJ IDEA.
  User: Mason
  Date: 11/12/2023
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  String error = request.getParameter("error");
    String errorMessage = "";

    if(error != null) {
        switch (error) {
            case "1":
                errorMessage = "Account username already exists! Please use a different username.";
                break;
            case "2":
                errorMessage = "Unexpected error. Please contact the web master.";
                break;
        }
    }

%>

<html>
<head>
    <title>Register New Account</title>
    <jsp:include page="components/styling.jsp"/>
</head>
<body>
<%-- Here you put the content for this page --%>

<div>
    <form method = "post" action = "registerAccountServlet">
        <div class="form-group">
            <label for = "txt_reg_username"> Username: </label>
            <input class= "form-control" type = "email" name = "txt_reg_username" id = "txt_reg_username" required />
        </div>
        <div class="form-group">
            <label for = "txt_reg_pw"> Password: </label>
            <input class = "form-control" type= "password" name = "txt_reg_pw" id ="txt_reg_pw" autocomplete = "off" required/>
        </div>
        <div class="form-group">
            <label for = "txt_check_pw"> Re-enter password: </label>
            <input class = "form-control" type= "password" name = "txt_check_pw" id = "txt_check_pw" autocomplete = "off" required/>
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

<% if(errorMessage.length()>0){ %>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                <strong>Error!</strong> <%= errorMessage %>
            </div>
        </div>
    </div>
</div>
<% } /*close if*/%>


<br/><br/>




<script type="text/javascript">
function checkRegisterForm(){
    let pass = document.getElementById("txt_reg_pw").value;
    let conf = document.getElementById("txt_check_pw").value;
    if(pass === conf){
        //Typed text in Password and Confirm Password are the same
        return true;
    } else {
        //Typed text in Password and Confirm Password do not match.
        window.alert("Passwords do not match. Please make sure that you entered the same password!");
        return false;
    }
}
</script>
</body>
</html>