<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="main.commands.CommandName" %>
<%@page import="main.Path" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Головна</title>
    <link href="css/startStyles.css" rel="stylesheet" type="text/css">
</head>
<body>

<div id="header">
    <table>
        <tr>
            <td><a href="${Path.PAGE__START}" class="logo">dlvr.</a></td>
            <td><button name="command" value="${CommandName.COMMAND__DISHES}" form="redirectForm">
                Меню страв
            </button></td>
            <td><button name="command" value="${CommandName.COMMAND__SHOW_ORDER_DISHES}" form="redirectForm">
                Оформити замовлення
            </button></td>
            <td><button name="command" value="${CommandName.COMMAND__SHOW_LOGIN}" form="redirectForm">
                ${sessionScope.user == null ? "Увійти" : "Привіт, ".concat(sessionScope.user.username).concat("!")}
            </button></td>
        </tr>
    </table>
    <form id="redirectForm" action="controller"></form>
</div>


<table style="height: 30vh">
    <tr>
        <td>
        </td>
    </tr>
</table>


</body>
</html>