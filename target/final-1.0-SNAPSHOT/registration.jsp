<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="main.commands.CommandName" %>
<%@page import="main.Path" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>final</title>
    <link href="css/startStyles.css" rel="stylesheet" type="text/css">
</head>
<body>

    <div id="header">
        <table>
            <tr>
                <td><a href="${Path.PAGE__START}" class="logo">dlvr.</a></td>
                <td><button name="command" value="${CommandName.COMMAND__SHOW_LOGIN}"
                            form="redirectForm">Увійти</button></td>
            </tr>
        </table>
        <form id="redirectForm" action="controller"></form>
    </div>

    <div class="floating-div">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandName.COMMAND__REGISTER}"/>
            <table>
                <tr class="floating-row">
                    <td>
                        <label for="usernameInput"></label>
                        <input class="floating-input" type="text" name="username"
                               placeholder="Username" id="usernameInput"
                               value="${sessionScope.username == null ? "" : sessionScope.username}" required>
                    </td>
                </tr>
                <c:if test="${not empty sessionScope.wrongUsername}">
                    <tr class="error-message">
                        <td>
                            ${sessionScope.wrongUsername}
                        </td>
                    </tr>
                </c:if>
                <tr class="floating-row">
                    <td>
                        <label for="emailInput"></label>
                        <input class="floating-input" type="email" name="email" placeholder="E-Mail"
                               id="emailInput" required>
                    </td>
                </tr>
                <c:if test="${not empty sessionScope.wrongEmail}">
                    <tr class="error-message">
                        <td>
                                ${sessionScope.wrongEmail}
                        </td>
                    </tr>
                </c:if>
                <tr class="floating-row">
                    <td>
                        <label for="passwordInput"></label>
                        <input class="floating-input" type="password" name="password" placeholder="Password"
                               id="passwordInput" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$" required>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td>
                        <label for="repeatedPassword"></label>
                        <input class="floating-input" type="password" name="repeatedPassword"
                               placeholder="Repeat password" id="repeatedPassword" required>
                    </td>
                </tr>
                <c:if test="${not empty sessionScope.wrongRepeatedPassword}">
                    <tr class="error-message">
                        <td>
                                ${sessionScope.wrongRepeatedPassword}
                        </td>
                    </tr>
                </c:if>
                <tr class="floating-row">
                    <td>
                        <input class="floating-button" id="submitButton" type="submit" value="Увійти">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <table style="height: 30vh">
        <tr>
            <td>
            </td>
        </tr>
    </table>
</body>
</html>