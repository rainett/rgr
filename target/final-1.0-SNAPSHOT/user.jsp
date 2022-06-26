<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="main.commands.CommandNames" %>
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
            <tr><td><a href="${Path.PAGE__START}" class="logo">dlvr.</a></td></tr>
        </table>
    </div>

    <div class="floating-div">
        <table>
            <tr class="floating-row">
                <td>
                    <button class="floating-button" name="command" form="redirectForm"
                            value="${CommandNames.COMMAND__ORDERS}">Активні замовлення</button>
                </td>
            </tr>
            <tr class="floating-row">
                <td>
                    <button class="floating-button" name="command" form="redirectForm"
                            value="${CommandNames.COMMAND__ADDRESSES}">Адреси доставки</button>
                </td>
            </tr>
            <tr class="floating-row">
                <td>
                    <button class="floating-button" name="command" form="redirectForm"
                            value="${CommandNames.COMMAND__PAYMENTS}">Способи оплати</button>
                </td>
            </tr>
            <tr class="floating-row">
                <td>
                    <button class="floating-button-danger" name="command" form="redirectForm"
                            value="logout">Вийти</button>
                </td>
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