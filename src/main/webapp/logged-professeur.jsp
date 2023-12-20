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
    <title>devSchool - prof</title>
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
            <a href="users-servlet?category=professeur">Liste des professeurs</a>
        </li>
        <li>
            <a href="users-servlet?category=eleve">Liste des élèves</a>
        </li>
        <li>
            <a href="cours-servlet">Liste des cours</a>
        </li>
        <li>
            <a style="color:red;" href="deco-servlet">Déconnexion</a>
        </li>
    </ul>
</div>
</body>
</html>
