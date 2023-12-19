<%--
  Created by IntelliJ IDEA.
  User: vincentconstantin
  Date: 19/12/2023
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>devSchool - élève</title>
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
<div class="content">
    <h1>Bienvenue
        <% if (session.getAttribute("username") != null) { %>
        <span class="username"><%=session.getAttribute("username")%></span>
        <% } else {
            response.sendRedirect("index.jsp");
        }
        %>
    </h1>
    <ul>
        <li>
            <a href="">Voir mes notes</a>
        </li>
        <li>
            <a style="color:red;" href="deco-servlet">Déconnexion</a>
        </li>
    </ul>
</div>
</body>
</html>
