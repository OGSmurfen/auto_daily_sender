<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
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
</body>
</html>