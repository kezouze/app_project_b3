<%@ page import="com.example.app_project_b3.Cours" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: vincentconstantin
  Date: 14/11/2023
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parcourseum</title>
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
<div class="content">

    <h1>Liste des cours</h1>
    <ul>
        <%
            List<Cours> cours = (List<Cours>) request.getAttribute("cours");
            for (Cours cours_2 : cours) {
        %>
        <li>
            <span><%= cours_2.getName() + " dispensé par " + cours_2.getLasName() + " " + cours_2.getFirstName() %></span>
        </li>
        <%
            }
        %>
    </ul>
    <a href="logged-professeur.jsp">Retour</a>
</div>
</body>
</html>
