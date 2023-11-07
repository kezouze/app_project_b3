<%@ page import="com.example.app_project_b3.Eleve" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: vincentconstantin
  Date: 07/11/2023
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parcourseum</title>
</head>
<body>
  <h1>Liste des élèves</h1>
<ul>
  <%
      List<Eleve> eleves = (List<Eleve>) request.getAttribute("eleves");
      for (Eleve eleve : eleves ) {
  %>
      <li><%= eleve.getLastName() + " " + eleve.getFirstName() %>
          <a href="delete-servlet?id=<%=eleve.getId()%>&table=eleves">🗑️</a>
      </li>
  <%
    }
  %>
</ul>
  <h2>Ajouter un élève</h2>
  <form method="post" action="insert-eleve-servlet">
      <input type="hidden" name="table" value="eleves">
      <input required type="text" placeholder="Nom" name="last_name">
      <input required type="text" placeholder="Prénom" name="first_name">
      <input type="submit" value="Valider">
  </form>
  <a href="index.jsp">Retour</a>
</body>
</html>
