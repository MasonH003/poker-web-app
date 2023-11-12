<%--
  Created by IntelliJ IDEA.
  User: Mason
  Date: 11/12/2023
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Register New Account</title>
</head>
<body>
<%-- Here you put the content for this page --%>

<form method = "post" action = "registerAccountServlet">
    <label for = "txt_reg_username"> Username: </label>
    <input class= "form-control" type = "email" name = "txt_reg_username" id = "txt_reg_username" required />
    <label for = "txt_reg_pw"> Password: </label>
    <input class = "form-control" type= "password" name = "txt_reg_pw" id ="txt_reg_pw" autocomplete = "off" required/>
    <label for = "txt_check_pw"> Re-enter password: </label>
    <input class = "form-control" type= "password" name = "txt_check_pw" id = "txt_check_pw" autocomplete = "off" required/>

    <div class="row mb-2">
        <div class="offset-md-1 col-12 col-md-2 d-grid">
            <button type="submit" onclick="return checkRegisterForm()" class="btn btn-success me-2"> Register</button>
        </div>
        <div class="col-12 col-md-2 d-grid">
            <button type="reset" class="btn btn-warning me-2" id="clear_button"> Clear</button>
        </div>
        <div class="col-12 col-md-2 d-grid">
            <a type="button" class="btn btn-danger me-2" href="index.jsp"> Cancel</a>
        </div>
    </div>
</form>

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