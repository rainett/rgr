<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="main.commands.CommandName" %>
<%@page import="main.Path" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Вхід" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>

    <div id="header">
        <table>
            <tr>
                <td><a href="${Path.PAGE__START}" class="logo">dlvr.</a></td>
                <td><button name="command" value="${CommandName.COMMAND__SHOW_REGISTER}" form="redirectForm">Реєстрація</button></td>
            </tr>
        </table>
        <form id="redirectForm" action="controller"></form>
    </div>
    <div class="floating-div">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandName.COMMAND__LOGIN}"/>
            <table>
                <tr class="floating-row">
                    <td>
                        <label>
                            <input class="floating-input" type="text" name="username" placeholder="Username" required>
                        </label>
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
                        <label>
                            <input class="floating-input" type="password" name="password" placeholder="Password" required>
                        </label>
                    </td>
                </tr>
                <c:if test="${not empty sessionScope.wrongPassword}">
                    <tr class="error-message">
                        <td>
                                ${sessionScope.wrongPassword}
                        </td>
                    </tr>
                </c:if>
                <tr class="floating-row"><td><input class="floating-button" type="submit" value="Увійти"></td></tr>
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