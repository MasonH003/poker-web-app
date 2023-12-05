<%--
  Created by IntelliJ IDEA.
  User: Mason
  Date: 12/5/2023
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.pokerwebapp.Card"%>
<html>
<head>
    <title>Hand Evaluation Demo</title>
</head>
<body>

<font size="+2">
<form method = "post" action = "demoHandEvaluationServlet">
    <input type="radio" id="two" name="rank1" value="2">
    <label for="two">2</label>
    <input type="radio" id="three" name="rank1" value="3">
    <label for="three">3</label>
    <input type="radio" id="four" name="rank1" value="4">
    <label for="four">4</label>
    <input type="radio" id="five" name="rank1" value="5">
    <label for="five">5</label>
    <input type="radio" id="six" name="rank1" value="6">
    <label for="six">6</label>
    <input type="radio" id="seven" name="rank1" value="7">
    <label for="seven">7</label>
    <input type="radio" id="eight" name="rank1" value="8">
    <label for="eight">8</label>
    <input type="radio" id="nine" name="rank1" value="9">
    <label for="nine">9</label>
    <input type="radio" id="ten" name="rank1" value="10">
    <label for="ten">10</label>
    <input type="radio" id="jack" name="rank1" value="J">
    <label for="jack">Jack</label>
    <input type="radio" id="queen" name="rank1" value="Q">
    <label for="queen">Queen</label>
    <input type="radio" id="king" name="rank1" value="K">
    <label for="king">King</label>
    <input type="radio" id="ace" name="rank1" value="A">
    <label for="ace">Ace</label><br>

    <input type="radio" id="diamond" name="suit1" value="D">
    <label for="diamond">Diamond</label>
    <input type="radio" id="spade" name="suit1" value="S">
    <label for="spade">Spade</label>
    <input type="radio" id="heart" name="suit1" value="H">
    <label for="heart">Heart</label>
    <input type="radio" id="club" name="suit1" value="C">
    <label for="club">Club</label><br>
    <br><br>

    <input type="radio" id="two2" name="rank2" value="2">
    <label for="two2">2</label>
    <input type="radio" id="three2" name="rank2" value="3">
    <label for="three2">3</label>
    <input type="radio" id="four2" name="rank2" value="4">
    <label for="four2">4</label>
    <input type="radio" id="five2" name="rank2" value="5">
    <label for="five2">5</label>
    <input type="radio" id="six2" name="rank2" value="6">
    <label for="six2">6</label>
    <input type="radio" id="seven2" name="rank2" value="7">
    <label for="seven2">7</label>
    <input type="radio" id="eight2" name="rank2" value="8">
    <label for="eight2">8</label>
    <input type="radio" id="nine2" name="rank2" value="9">
    <label for="nine2">9</label>
    <input type="radio" id="ten2" name="rank2" value="10">
    <label for="ten2">10</label>
    <input type="radio" id="jack2" name="rank2" value="J">
    <label for="jack2">Jack</label>
    <input type="radio" id="queen2" name="rank2" value="Q">
    <label for="queen2">Queen</label>
    <input type="radio" id="king2" name="rank2" value="K">
    <label for="king2">King</label>
    <input type="radio" id="ace2" name="rank2" value="A">
    <label for="ace2">Ace</label><br>

    <input type="radio" id="diamond2" name="suit2" value="D">
    <label for="diamond2">Diamond</label>
    <input type="radio" id="spade2" name="suit2" value="S">
    <label for="spade2">Spade</label>
    <input type="radio" id="heart2" name="suit2" value="H">
    <label for="heart2">Heart</label>
    <input type="radio" id="club2" name="suit2" value="C">
    <label for="club2">Club</label><br>
    <br><br>


    <input type="radio" id="two3" name="rank3" value="2">
    <label for="two3">2</label>
    <input type="radio" id="three3" name="rank3" value="3">
    <label for="three3">3</label>
    <input type="radio" id="four3" name="rank3" value="4">
    <label for="four3">4</label>
    <input type="radio" id="five3" name="rank3" value="5">
    <label for="five3">5</label>
    <input type="radio" id="six3" name="rank3" value="6">
    <label for="six3">6</label>
    <input type="radio" id="seven3" name="rank3" value="7">
    <label for="seven3">7</label>
    <input type="radio" id="eight3" name="rank3" value="8">
    <label for="eight3">8</label>
    <input type="radio" id="nine3" name="rank3" value="9">
    <label for="nine3">9</label>
    <input type="radio" id="ten3" name="rank3" value="10">
    <label for="ten3">10</label>
    <input type="radio" id="jack3" name="rank3" value="J">
    <label for="jack3">Jack</label>
    <input type="radio" id="queen3" name="rank3" value="Q">
    <label for="queen3">Queen</label>
    <input type="radio" id="king3" name="rank3" value="K">
    <label for="king3">King</label>
    <input type="radio" id="ace3" name="rank3" value="A">
    <label for="ace3">Ace</label><br>

    <input type="radio" id="diamond3" name="suit3" value="D">
    <label for="diamond3">Diamond</label>
    <input type="radio" id="spade3" name="suit3" value="S">
    <label for="spade3">Spade</label>
    <input type="radio" id="heart3" name="suit3" value="H">
    <label for="heart3">Heart</label>
    <input type="radio" id="club3" name="suit3" value="C">
    <label for="club3">Club</label><br>

    <br><br>

    <input type="radio" id="two4" name="rank4" value="2">
    <label for="two4">2</label>
    <input type="radio" id="three4" name="rank4" value="3">
    <label for="three4">3</label>
    <input type="radio" id="four4" name="rank4" value="4">
    <label for="four4">4</label>
    <input type="radio" id="five4" name="rank4" value="5">
    <label for="five4">5</label>
    <input type="radio" id="six4" name="rank4" value="6">
    <label for="six4">6</label>
    <input type="radio" id="seven4" name="rank4" value="7">
    <label for="seven4">7</label>
    <input type="radio" id="eight4" name="rank4" value="8">
    <label for="eight4">8</label>
    <input type="radio" id="nine4" name="rank4" value="9">
    <label for="nine4">9</label>
    <input type="radio" id="ten4" name="rank4" value="10">
    <label for="ten4">10</label>
    <input type="radio" id="jack4" name="rank4" value="J">
    <label for="jack4">Jack</label>
    <input type="radio" id="queen4" name="rank4" value="Q">
    <label for="queen4">Queen</label>
    <input type="radio" id="king4" name="rank4" value="K">
    <label for="king4">King</label>
    <input type="radio" id="ace4" name="rank4" value="A">
    <label for="ace4">Ace</label><br>

    <input type="radio" id="diamond4" name="suit4" value="D">
    <label for="diamond4">Diamond</label>
    <input type="radio" id="spade4" name="suit4" value="S">
    <label for="spade4">Spade</label>
    <input type="radio" id="heart4" name="suit4" value="H">
    <label for="heart4">Heart</label>
    <input type="radio" id="club4" name="suit4" value="C">
    <label for="club4">Club</label><br>

    <br><br>

    <input type="radio" id="two5" name="rank5" value="2">
    <label for="two5">2</label>
    <input type="radio" id="three5" name="rank5" value="3">
    <label for="three5">3</label>
    <input type="radio" id="four5" name="rank5" value="4">
    <label for="four5">4</label>
    <input type="radio" id="five5" name="rank5" value="5">
    <label for="five5">5</label>
    <input type="radio" id="six5" name="rank5" value="6">
    <label for="six5">6</label>
    <input type="radio" id="seven5" name="rank5" value="7">
    <label for="seven5">7</label>
    <input type="radio" id="eight5" name="rank5" value="8">
    <label for="eight5">8</label>
    <input type="radio" id="nine5" name="rank5" value="9">
    <label for="nine5">9</label>
    <input type="radio" id="ten5" name="rank5" value="10">
    <label for="ten5">10</label>
    <input type="radio" id="jack5" name="rank5" value="J">
    <label for="jack5">Jack</label>
    <input type="radio" id="queen5" name="rank5" value="Q">
    <label for="queen5">Queen</label>
    <input type="radio" id="king5" name="rank5" value="K">
    <label for="king5">King</label>
    <input type="radio" id="ace5" name="rank5" value="A">
    <label for="ace5">Ace</label><br>

    <input type="radio" id="diamond5" name="suit5" value="D">
    <label for="diamond5">Diamond</label>
    <input type="radio" id="spade5" name="suit5" value="S">
    <label for="spade5">Spade</label>
    <input type="radio" id="heart5" name="suit5" value="H">
    <label for="heart5">Heart</label>
    <input type="radio" id="club5" name="suit5" value="C">
    <label for="club5">Club</label><br>

    <div class="offset-md-1 col-12 col-md-2 d-grid">
        <button type="submit" class="blue-button"> Submit</button>
    </div>
</form>
</font>
 <br><br><br>


<%
    // Retrieve the handType attribute from the request object
    String handType = (String) session.getAttribute("handType");

    // Display the handType value if it is not null

%>
<font size="+4"><p>Hand Evaluation: <%= handType %></p></font>
<%
    // Assuming you have Card objects stored as attributes card1, card2, card3, card4, and card5
    Card card1 = (Card) session.getAttribute("demoCard1");
    Card card2 = (Card) session.getAttribute("demoCard2");
    Card card3 = (Card) session.getAttribute("demoCard3");
    Card card4 = (Card) session.getAttribute("demoCard4");
    Card card5 = (Card) session.getAttribute("demoCard5");


%>

<!-- Display card images -->
<%
    if( card1 != null ) {
%>
<img src="<%= card1.generateImageUrl() %>" width="250" height ="363" alt="Card 1">
<%
    }
if( card2 != null ) {
%>
<img src="<%= card2.generateImageUrl() %>" width="250" height ="363" alt="Card 2">
<%
    }
if( card3 != null ) {
%>
<img src="<%= card3.generateImageUrl() %>" width="250" height ="363" alt="Card 3">
<%
    }
if( card4 != null ) {
%>
<img src="<%= card4.generateImageUrl() %>" width="250" height ="363" alt="Card 4">
<%
    }
if( card5 != null ) {
%>
<img src="<%= card5.generateImageUrl() %>" width="250" height ="363" alt="Card 5">
<% } %>
</body>
</html>
