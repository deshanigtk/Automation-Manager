<%--
  Created by IntelliJ IDEA.
  User: deshani
  Date: 8/15/17
  Time: 12:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="index.jsp"><h1>Scanners</h1></a>
<a href="staticScanner.jsp"><h2>Static Scanner</h2></a>
<a href="#"><h3>Upload Product</h3></a>
<form action="/cloneProduct">
    <h4>Clone From GitHub</h4>
    <label>GitHub URL</label>
    <input type="url" name="gitUrl">

    <label>Branch</label>
    <input name="branch">
    <button>Clone</button>
</form>
<form action="upload" method="post" enctype="multipart/form-data">
    <h4>Upload Zip File</h4>
    <input type="file" name="file"/>
    <button>Upload</button>
</form>
</body>
</html>
