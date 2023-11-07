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
</head>
<body>
<h1>Liste des professeurs</h1>
<ul>
    <%
        List<Prof> profs = (List<Prof>) request.getAttribute("profs");
        for (Prof prof : profs ) {
    %>
        <li><%= prof.getLastName() + " "+ prof.getFirstName() %></li>
    <%
        }
    %>
</ul>
</body>
</html>
