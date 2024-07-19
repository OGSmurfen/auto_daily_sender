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
<p>This is your account. </p>
<p>Here u see ur money or whatever this website is for...</p>
<p>bbye</p>

<br/>
<br/>

<form action="${pageContext.request.contextPath}/SendMailServlet" method="post">

    <label for="mailcontent">Body of email:</label>
    <textarea id="mailcontent" name="mailcontent" rows="4" cols="50">
        Write body!
    </textarea>
    <label for="recipient">Recipient: </label><input type="text" name="recipient" id="recipient">
    <input type="submit" value="Send">

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
