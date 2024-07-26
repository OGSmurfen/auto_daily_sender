<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>webapp</title>
  <link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
<h1><%= "Automatic daily sender app!" %></h1>
<br/>
<%--<a href="hello-servlet">GetAll Users (in development only)</a>--%>
<br/>
<br/>
<br/>
<form action="${pageContext.request.contextPath}/LoginServlet" method="post" autocomplete="off">
  <label for="username">Username: </label><input type="text" name="username" id="username">
  <br/>
  <label for="password">Password: </label><input type="password" name="password" id="password">
  <br/>
  <br/>
  <input type="submit" value="Login">
  <button type="submit" formaction="register.jsp">Register</button>
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