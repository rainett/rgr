<%@ page import="main.commands.CommandName" %>
<%@page import="main.Path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <button name="command" value="${CommandName.COMMAND__SHOW_LOGIN}">
                            Особистий кабінет
                        </button>
                    </form>
                </td>
            </tr>
        </table>
    </div>

    <div class="floating-div">
        <table>
            <c:if test="${empty requestScope.payments}">
                <tr class="floating-row-l">
                    <td>Ви ще не додали жодної карти!</td>
                </tr>
            </c:if>
            <c:if test="${not empty requestScope.payments}">
                <tr class="floating-row">
                    <td>Номер</td>
                    <td>Дійсна до</td>
                </tr>
            </c:if>
            <c:forEach items="${requestScope.payments}" var="p">
                <tr class="floating-row-clickable" onclick="document.forms['updateForm${p.id}'].submit();" >
                    <td>
                        <form action="controller" id="updateForm${p.id}">
                            ${p.number.substring(0, p.number.length()/2)}********
                                <input type="hidden" name="command"
                                       value="${CommandName.COMMAND__SHOW_UPDATE_PAYMENT}">
                                <input type="hidden" name="paymentId"
                                       value="${p.id}">
                        </form>
                    </td>
                    <td>${p.till.substring(0, p.till.length()/2)}/**</td>
                </tr>
            </c:forEach>
            <tr class="floating-row">
                <td <c:if test="${not empty requestScope.payments}"> colspan="2" </c:if> >
                    <button class="floating-button" name="command"
                            value="${CommandName.COMMAND__SHOW_NEW_PAYMENT}" form="newPaymentForm">
                        <input type="hidden" name="resp" value="payments" form="newPaymentForm">
                        Нова картка
                    </button>
                </td>
            </tr>
        </table>
        <form id="newPaymentForm" action="controller"></form>
    </div>
    <table style="height: 30vh">
        <tr>
            <td>
            </td>
        </tr>
    </table>
</body>
</html>