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
                <td><button name="command" value="${CommandName.COMMAND__USER}" form="redirectForm">
                    Особистий кабінет
                </button></td>
            </tr>
        </table>
        <form id="redirectForm" action="controller"></form>
    </div>
    <div class="floating-div">
        <table>
            <tr class="floating-row-s">
                <td>
                    Ім'я користувача:<br>
                    ${requestScope.user.username}
                </td>
                <td>
                    Бажана посада:<br>
                    ${requestScope.application.roleId}
                </td>
            </tr>
            <tr class="floating-row-s">
                <td>
                    Електронна пошта:<br>
                    ${requestScope.user.email}
                </td>
                <td>
                    Стан заявки:<br>
                    ${requestScope.application.state == true ? "Розглянута" : "Нерозглянута"}
                </td>
            </tr>
            <c:if test="${requestScope.application.state == false}">
            <tr class="floating-row">
                <td>
                    <button class="floating-button-danger" form="responseForm" name="response" value="decline">
                        Відхилити
                    </button>
                </td>
                <td>
                    <button class="floating-button" form="responseForm" name="response" value="approve">
                        Прийняти
                    </button>
                </td>
            </tr>
            </c:if>
        </table>
        <form id="responseForm" action="controller" method="post">
            <input type="hidden" name="command" value="${CommandName.COMMAND__APPLICATION_PROCESS}">
            <input type="hidden" name="applicationId" value="${requestScope.application.id}">
            <input type="hidden" name="userId" value="${requestScope.user.id}">
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