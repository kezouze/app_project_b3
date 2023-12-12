<%--
  Created by IntelliJ IDEA.
  User: vincentconstantin
  Date: 12/12/2023
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logged</title>
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
<div class="content">
    <h1>Bienvenue
        <% if (request.getAttribute("username") != null) { %>
        <span class="username"><%=request.getAttribute("username")%></span>
        <% }%>
    </h1>
    <ul>
        <li>
            <a href="profs-servlet">Liste des professeurs</a>
        </li>
        <li>
            <a href="eleves-servlet">Liste des élèves</a>
        </li>
        <li>
            <a href="cours-servlet">Liste des cours</a>
        </li>
    </ul>
</div>
</body>
</html>
