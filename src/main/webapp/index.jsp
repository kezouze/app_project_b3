<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Parcourseum</title>
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>
    <div class="content">
        <!-- pour récupérer resultcount <p><%=request.getAttribute("resultCount")%></p> -->
        <h1 class="devSchool-text">devSchool</h1>
        <button class="con-btn" onclick="showHiddenForm()">Connexion</button>
        <form action="connection-servlet" method="post" id="hiddenConForm" style="display: none">
            <input placeholder="Identifiant" type="text" name="username">
            <input placeholder="Mot de passe" type="password" name="password">
            <input type="submit">
        </form>
        <% if (request.getAttribute("login-error") != null ){ %>
        <p class="login-error">Identifiants incorrects</p>
        <% }%>
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
<script>
    let hiddenForm = document.getElementById("hiddenConForm")
    function showHiddenForm(){
        hiddenForm.style.display = "flex"
    }
</script>
</html>