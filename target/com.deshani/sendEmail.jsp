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
<form action="mail" method="post">
    To:<input type="text" name="to"  /><br/>
    Subject:<input type="text" name="subject"  /><br/>
    Message:<input type="text" name="message"  /><br/>
    Your Email id:<input type="text" name="user" ><br/>
    Password<input type="password" name="pass"   /><br/>
    <input type="submit" value="send" />
</form>
</body>
</html>
