<%@ page import="main.Path" %>
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
        response.sendRedirect(Path.PAGE__USER);
    }
%>

    <div id="header">
        <table>
            <tr>
                <td><a href="<%=Path.PAGE__START%>" class="logo">dlvr.</a></td>
                <td><button id="login" onclick="document.location.href='<%=Path.PAGE__LOGIN%>'">Увійти</button></td>
            </tr>
        </table>
    </div>
    <div class="floatingMenu">
        <form action="controller" onsubmit="return validate()" method="post">
            <input type="hidden" name="command" value="register"/>
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