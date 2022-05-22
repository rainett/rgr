<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ресторан</title>
    <link href="css/startStyles.css" rel="stylesheet" type="text/css">
</head>
<body>

    <%
        String loginButtonText = "Увійти";
        if (session.getAttribute("username") != null) {
            loginButtonText = String.format("Hi, %s!", session.getAttribute("username"));
        }
    %>

    <div id="header">
        <a href="http://localhost:8080/final/" class="logo">dlvr.</a>
        <button id="login" onclick="document.location.href='login.jsp'"><%=loginButtonText%></button>
    </div>
</body>
</html>