<%--
  Created by IntelliJ IDEA.
  User: deshani
  Date: 8/17/17
  Time: 10:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpServlet, javax.servlet.http.HttpServletResponse, classes.MainController" %>
<%@ page import="java.awt.*" %>
<html>
<head>
    <title>Automation Manager</title>
</head>
<body>
<a href="index.jsp"><h1>Scanners</h1></a>
<h2>Static Scanner</h2>
<p>Pull Static Scanner Docker image and start the container</p>

<!--Static Scanner status= "not started" -> A button to start scanner -->
<% if ("not started".equals(MainController.getStaticScannerStatus())) { %>
<form action="/staticScanner">
    <button name="startContainer"> Click Here to Start the Container</button>

</form>

<!--Static Scanner status= "running" -> Buttons to stop scanner, remove scanner -->
<% } else if ("running".equals(MainController.getStaticScannerStatus())) { %>
<form action="/stopStaticScanner">
    <button name="stopContainer"> Click Here to STOP the Container</button>

</form>

<form action=/removeStaticScanner">
    <button name="removeContainer"> Click Here to Remove the Container</button>
</form>
<p>Static Scanner is already started. Continue the Scan by uploading the product
    <a href="staticScannerProductUpload.jsp"><h3>Upload Product</h3></a>
</p>

<!--Static Scanner status= "stopped" -> Buttons to restart scanner, remove scanner -->
<% } else if ("stopped".equals(MainController.getStaticScannerStatus())) { %>
<form action="/restartStaticScanner">
    <button name="restartContainer"> Click Here to restart the Container</button>

</form>

<form action="/removeStaticScanner">
    <button name="removeContainer"> Click Here to Remove the Container</button>
</form>

<p>Static Scanner is already started. Continue the Scan by uploading the product
    <a href="staticScannerProductUpload.jsp"><h3>Upload Product</h3></a>
</p>
<% }%>

</body>
</html>
