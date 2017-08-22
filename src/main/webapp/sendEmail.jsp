<%--
  Created by IntelliJ IDEA.
  User: deshani
  Date: 8/16/17
  Time: 4:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send Email</title>
</head>
<body>

<form action="/sendEmail" method="post">
    <% String scanType=request.getParameter("scanType");%>
    <label>To</label><br>
    <input name="to"/><br>
    <label>Subject</label><br>
    <input name="subject"/><br>

    <label>Message</label><br>
    <input name="message"/><br>

    <input name="user" value="codeophrenia@gmail.com" hidden>
    <input name="pass" value="qoqlgzcnwoddinga" hidden>

    <% request.setAttribute("scantype",scanType);%>
    <input type="submit" value="send"/>
</form>
</body>
</html>
