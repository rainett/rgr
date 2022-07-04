<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="main.Path" %>
<%@page import="main.commands.CommandName" %>
<!DOCTYPE html>
<html>
<head>
    <title>final</title>
    <link href="${pageContext.request.contextPath}/css/startStyles.css" rel="stylesheet" type="text/css">
</head>
<body>

<div id="header">
    <table>
        <tr>
            <td><a href="${Path.PAGE__START}" class="logo">dlvr.</a></td>
            <td>
                <form action="controller">
                    <button name="command" value="${CommandName.COMMAND__SHOW_LOGIN}">
                        Особистий кабінет
                    </button>
                </form>
            </td>
        </tr>
    </table>
</div>

    <div class="floating-div">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandName.COMMAND__UPDATE_PAYMENT}"/>
            <input type="hidden" name="paymentId" value="${requestScope.payment.id}"/>
            <table>
                <tr class="floating-row">
                    <td colspan="2">
                        <label>
                            <input class="floating-input" type="text" name="number" pattern="^[0-9]{16}$"
                                   placeholder="Number" value="${requestScope.payment.number}" required>
                        </label>
                    </td>
                </tr>
                <c:if test="${not empty sessionScope.wrongNumber}">
                    <tr class="error-message">
                        <td colspan="2">
                                ${sessionScope.wrongNumber}
                        </td>
                    </tr>
                </c:if>
                <tr class="floating-row">
                    <td>
                        <label>
                            <input class="floating-input" type="text" name="till"
                                   pattern="(0[1-9]|1[0-2])/[0-9]{2}"
                                   placeholder="Expiration date" value="${requestScope.payment.till}" required>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input class="floating-input" type="password" name="cvv" pattern="^[0-9]{3}$"
                                   placeholder="CVV" value="${requestScope.payment.cvv}" required>
                        </label>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="2">
                        <input class="floating-button" type="submit" value="Зберегти">
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="2">
                        <input class="floating-button-danger" type="submit"
                               value="Видалити картку" form="deleteCard">
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