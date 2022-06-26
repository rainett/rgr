<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="main.Path" %>
<%@page import="main.commands.CommandNames" %>
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
            <input type="hidden" name="command" value="${CommandNames.COMMAND__UPDATE_PAYMENT}"/>
            <input type="hidden" name="paymentId" value="${requestScope.payment.id}"/>
            <table>
                <tr class="floating-row">
                    <td colspan="2">
                        <label for="numberInput"></label><input class="floating-input" id="numberInput" type="text" name="number" value="${requestScope.payment.number}" placeholder="Number" required>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td>
                        <label for="tillInput"></label><input class="floating-input" id="tillInput" type="text" name="till" value="${requestScope.payment.till}" placeholder="Expiration date" required>
                    </td>
                    <td>
                        <label for="cvvInput"></label><input class="floating-input" id="cvvInput" type="password" name="cvv" placeholder="CVV" required>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="2">
                        <input class="floating-button" type="submit" value="Зберегти">
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="2">
                        <input class="floating-button-danger" type="submit" value="Видалити картку" form="deleteCard">
                    </td>
                </tr>
            </table>
        </form>
        <form id="deleteCard" action="controller" method="post">
            <input type="hidden" name="command" value="deletePayment"/>
            <input type="hidden" name="paymentId" value="${requestScope.payment.id}"/>
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