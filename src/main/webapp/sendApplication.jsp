<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="main.commands.CommandName" %>
<%@page import="main.Path" %>
<%@page import="main.db.entities.Role" %>
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
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandName.COMMAND__SEND_APPLICATION}"/>
            <table>
                <tr class="floating-row-s">
                    <td>
                        <label>
                            <input type="radio" value="${Role.COOK.roleStr}" name="vacancy">Кухар
                        </label>
                    </td>
                </tr>
                <tr class="floating-row-s">
                    <td>

                        <input id="${Role.COURIER.roleStr}" type="radio"
                               value="${Role.COURIER.roleStr}" name="vacancy">
                        <label for="${Role.COURIER.roleStr}">Кур'єр</label>
                    </td>
                </tr>
                <tr class="floating-row-s">
                    <td>
                        <label>
                            <input type="radio" value="${Role.MANAGER.roleStr}" name="vacancy">Менеджер
                        </label>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td>
                        <input type="submit" class="floating-button" value="Підтвердити">
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