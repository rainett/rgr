<%@page import="main.Path" %>
<%@page import="main.commands.CommandNames" %>
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
                <td>
                    <form action="controller">
                        <button name="command" value="${CommandNames.COMMAND__SHOW_LOGIN}">
                            Особистий кабінет
                        </button>
                    </form>
                </td>
            </tr>
        </table>
    </div>

    <div class="floating-div">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandNames.COMMAND__CONFIRM_ORDER}"/>
            <table>
                <tr class="floating-row">
                    <td colspan="2">
                        Підтверджуєте замовлення на суму ${sessionScope.orderA.price}?
                    </td>
                </tr>
                <tr class="floating-row">
                    <td><input class="floating-button-danger" type="submit" value="Відмінити" form="cancelForm"></td>
                    <td><input class="floating-button" id="submitButton" type="submit" value="Зберегти"></td>
                </tr>
            </table>
        </form>
        <form id="cancelForm" action="controller" method="post">
            <input type="hidden" name="command" value="${CommandNames.COMMAND__CANCEL_ORDER}"/>
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