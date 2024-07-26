
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>${username}'s Daily</title>
</head>
<body>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setHeader("Expires", "0"); // Proxies

    if (session.getAttribute("username") == null ) {
        response.sendRedirect("index.jsp");
    }
%>

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

</form>
<form action="${pageContext.request.contextPath}/LogoutServlet" method="get">
    <input type="submit" value="Logout">
</form>


<div style="color: red;">
    <%if(session.getAttribute("email") != null)
        out.print(session.getAttribute("email"));  %>
</div>




<%--<%--%>
<%--    String errorMessage = (String) request.getAttribute("errorMessage");--%>
<%--    if (errorMessage != null) {--%>
<%--%>--%>
<%--<div style="color: red;">--%>
<%--    <%= errorMessage %>--%>
<%--</div>--%>
<%--<%--%>
<%--    }--%>
<%--%>--%>

</body>
</html>
