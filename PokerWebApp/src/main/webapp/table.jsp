<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.pokerwebapp.model.entity.Account"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <jsp:include page="components/table-styling.jsp"/>
    <link rel="stylesheet" href="styles.css">
</head>

<body>
<%
    // Get session:

    // Account initialized:
    Account logged = (Account) session.getAttribute("Account");

    // RoundController initialized:

    // Start RoundController:
%>

<div style="position: absolute; top: 0; right: 0; padding: 10px;">

    <%
        String username;
        username = logged.getEmail();%>
    <%= "USER: " + username %>
</div>

<div class="table">

    <div class="table-area">
        <img src = "images/poker-table.png" class="center-image"/>

        <div style="position: fixed; top: 20px; left: 20px;">

            <!--
            <img src="images/playing-cards/card-sleeve.png" class="card"/>
            <img src="images/playing-cards/card-sleeve.png" class="card"/>
            <img src="images/playing-cards/9_of_spades.png" class="card"/>
            -->

        </div>
    </div>

    <div class="button-area">
        <div class="rounded-rectangle">
            <center>
                <a href="" class="blue-button">Fold</a>

                <br>

                <a href="" class="blue-button">Bet</a>

                <br>

                <a href="" class="blue-button">Call</a>

                <br>

                <a href="" class="blue-button">Raise</a>

                <br>
            </center>
        </div>
        <div class="rounded-rectangle">
            <center>
                <a href="index.jsp" class="red-button">Exit table</a>
            </center>
        </div>
    </div>

    <div class="logo-area">
        <img src = "images/Futuretech logo 3NEW.png" width="280" height="180"/>
    </div>

</div>





</body>

</html>
