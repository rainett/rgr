<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ресторан</title>
    <link href="css/startStyles.css" rel="stylesheet" type="text/css">
</head>
<body>

<%
    if (session.getAttribute("username") != null) {
        response.sendRedirect("userPage.jsp");
    }
%>

    <div id="header">
        <a href="http://localhost:8080/final/" class="logo" id="soloLogo">dlvr.</a>
    </div>
    <form action="login">
        <input type="text" name="username" placeholder="Username" required><br>
        <input type="password" name="password" placeholder="Password" required><br>
        <input id="submitButton" type="submit" value="Увійти">
    </form>
</body>
</html>