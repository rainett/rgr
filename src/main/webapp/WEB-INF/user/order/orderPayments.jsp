<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="main.commands.CommandName" %>
<%@page import="main.Path" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Оплата замовлення" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>

<%@include file="/WEB-INF/jspf/header.jspf"%>

    <div class="floating-div">
        <table>
            <c:if test="${empty requestScope.payments}">
            <tr class="floating-row">
                <td>Ви ще не додали жодної карти!</td>
            </tr>
            </c:if>
            <c:if test="${not empty requestScope.payments}">
            <tr class="floating-row-s">
                <td>Номер</td>
                <td>Дійсна до</td>
            </tr>
            </c:if>

            <c:forEach items="${requestScope.payments}" var="p">
                <tr class="floating-row-clickable" onclick="document.forms['redirectForm'].submit();">
                    <td>
                            ${p.number.substring(0, p.number.length()/2)}********
                        <input type="hidden" name="command"
                               value="${CommandName.COMMAND__SET_ORDER_PAYMENT}" form="redirectForm">
                        <input type="hidden" name="paymentId" value="${p.id}" form="redirectForm">
                    </td>
                    <td>${p.till.substring(0, p.till.length()/2)}/**</td>
                </tr>
            </c:forEach>

            <tr class="floating-row">
                <td <c:if test="${not empty requestScope.payments}"> colspan="2" </c:if> >
                    <form action="controller">
                        <button class="floating-button" name="command"
                                value="${CommandName.COMMAND__SHOW_NEW_PAYMENT}">
                            <input type="hidden" name="resp" value="order">
                            Нова картка
                        </button>
                    </form>
                </td>
            </tr>
        </table>
        <form id="redirectForm" action="controller" method="post"></form>
    </div>
    <table style="height: 30vh">
        <tr>
            <td>
            </td>
        </tr>
    </table>
</body>
</html>