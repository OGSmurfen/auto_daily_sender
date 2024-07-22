<%--
  Created by IntelliJ IDEA.
  User: papa_smurf
  Date: 7/15/2024
  Time: 11:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account information:</title>
</head>
<body>

<br/>
<h2>Welcome back, <b>${username}</b></h2>
<p>Please write down what you worked on today</p>
<p>Please also what you plan on doing tomorrow is</p>
<p>Please write down if you have any issues and what they are</p>
<br/>
<br/>

<form action="${pageContext.request.contextPath}/SendMailServlet" method="post">

    <label for="mailcontenttoday">Today:</label><br/>
    <textarea id="mailcontenttoday" name="mailcontenttoday" rows="4" cols="50">
    </textarea><br/>
    <label for="mailcontenttomorrow">Tomorrow:</label><br/>
    <textarea id="mailcontenttomorrow" name="mailcontenttomorrow" rows="4" cols="50">
    </textarea><br/>
    <label for="mailcontentissues">Issues:</label><br/>
    <textarea id="mailcontentissues" name="mailcontentissues" rows="4" cols="50">
    </textarea><br/>
<%--    <label for="recipient">Recipient: </label><input type="text" name="recipient" id="recipient">--%>
    <br/>
    <input type="submit" value="Send">
    <button type="submit" formaction="index.jsp">Logout</button>
</form>


<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
<div style="color: red;">
    <%= errorMessage %>
</div>
<%
    }
%>

</body>
</html>
