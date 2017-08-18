<%--
  Created by IntelliJ IDEA.
  User: deshani
  Date: 8/17/17
  Time: 2:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Automation Manager</title>
</head>
<body>
<a href="index.jsp"><h1>Scanners</h1></a>
<a href="staticScanner"><h2>Static Scanner</h2></a>
<a href="staticScanners.jsp"><h3>Static Scanning Methods</h3></a>
<form action="/findSecBugs">
    <button>Click to Start FindSecBugs</button>
</form>
</body>
</html>
