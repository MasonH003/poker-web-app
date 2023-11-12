<%--
  Created by IntelliJ IDEA.
  User: christophkp
  Date: 11/11/23
  Time: 4:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>

    <form method="post" action="addFriendServlet">
        <div class="mb-3">
            <input type="email" class="form-control" id="friend" name="friend">
        </div>
        <button type="submit" class="btn btn-primary">Add Friend</button>
    </form>


    <form method="post" action="removeFriendServlet">
        <div class="mb-3">
            <input type="email" class="form-control" id="remove" name="remove">
        </div>
        <button type="submit" class="btn btn-primary">Remove Friend</button>
    </form>

    <form method="post" action="blockAccountServlet">
        <div class="mb-3">
            <input type="email" class="form-control" id="block" name="block">
        </div>
        <button type="submit" class="btn btn-primary">Block Friend</button>
    </form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>



</html>
