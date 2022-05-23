<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ресторан</title>
    <link href="css/startStyles.css" rel="stylesheet" type="text/css">
</head>
<body>

    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        if (session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
        }
    %>

    <div id="header">
        <a href="http://localhost:8080/final/" class="logo" id="soloLogo">dlvr.</a>
    </div>

    <form action="logout">
        тут має бути обліковий запис юзера
        <input type="submit" value="Вийти">
    </form>
</body>
</html>