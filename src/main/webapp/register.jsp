<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: papa_smurf
  Date: 7/14/2024
  Time: 5:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1><%= "Register Here:" %></h1>
<br/>
<form action="${pageContext.request.contextPath}/RegisterServlet" method="post" autocomplete="off">
    <label for="username">Username: </label><input type="text" name="username" id="username">
    <br/>
    <label for="password">Password: </label><input type="password" name="password" id="password">
    <br/>
    <label for="confirmPassword">Confirm Password: </label><input type="password" name="confirmPassword" id="confirmPassword">
    <br/>
    <label for="email">Email: </label><input type="text" name="email" id="email">
    <br/>
    <br/>
    <input type="submit" value="Register">
    <button type="submit" formaction="index.jsp">Back To Login</button>
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


<% if(Objects.equals(errorMessage, "User created successfully")){ %>

<% } %>
</body>
</html>
