<%@ page import="java.util.List" %>
<%@ page import="com.example.app_project_b3.Prof" %>
<%--
  Created by IntelliJ IDEA.
  User: vincentconstantin
  Date: 07/11/2023
  Time: 09:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parcourseum</title>
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
<h1>Liste des professeurs</h1>
<ul>
    <%
        List<Prof> profs = (List<Prof>) request.getAttribute("profs");
        for (Prof prof : profs ) {
    %>
        <li class="profs-list">
            <span><%= prof.getLastName() + " "+ prof.getFirstName() %></span>
            <span>
                <a title="Modifier" href="modify-servlet?id=<%=prof.getId()%>&table=profs&last_name=<%=prof.getLastName()%>&first_name=<%=prof.getFirstName()%>">🖋️</a>
                <a title="Supprimer" href="delete-servlet?id=<%=prof.getId()%>&table=profs">🗑️</a>
            </span>
        </li>
    <%
        }
    %>
</ul>
<h2>Ajouter un prof</h2>
<form method="post" action="insert-servlet">
    <input type="hidden" name="table" value="profs">
    <input required type="text" placeholder="Nom" name="last_name">
    <input required type="text" placeholder="Prénom" name="first_name">
    <input type="submit" value="Valider">
</form>
<a href="index.jsp">Retour</a>
</body>
</html>
