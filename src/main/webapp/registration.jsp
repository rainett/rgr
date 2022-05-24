<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ресторан</title>
    <link href="css/startStyles.css" rel="stylesheet" type="text/css">
    <script src="scripts/script.js"></script>
</head>
<body>

<%
    if (session.getAttribute("username") != null) {
        response.sendRedirect("userPage.jsp");
    }

    if (session.getAttribute("failedToRegister") != null)  {

    }
%>

    <div id="header">
        <a href="http://localhost:8080/final/" class="logo">dlvr.</a>
        <button id="login" onclick="document.location.href='login.jsp'">Увійти</button>
    </div>
    <div class="floatingMenu">
        <form action="register" onsubmit="return validate()" method="post">
            <table>
                <%
                    if (session.getAttribute("failedToRegister") != null) {
                %>
                <tr><td id="wrongLogin">Користувач з таким іменем, чи e-mail'ом, вже існує</td></tr>
                <%
                    }
                %>
                <tr><td><input type="text" name="username" placeholder="Username" id="usernameInput" required></td></tr>
                <tr><td><input type="text" name="email" placeholder="E-Mail" id="emailInput" required></td></tr>
                <tr><td><input type="password" name="password" placeholder="Password" id="passwordInput" required></td></tr>
                <tr><td><input type="password" name="repeatedPassword" placeholder="Repeat password" id="repeatedPassword" required></td></tr>
                <tr><td><input id="submitButton" type="submit" value="Увійти"></td></tr>
            </table>
        </form>
    </div>
</body>
</html>