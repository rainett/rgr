<%@ page import="main.commands.CommandName" %>
<%@page import="main.Path" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Адреса замовлення" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>

<%@include file="/WEB-INF/jspf/header.jspf"%>

    <div class="floating-div">
        <table>
            <c:if test="${empty requestScope.addresses}">
                <tr class="floating-row">
                    <td>Ви ще не додали жодної адреси!</td>
                </tr>
            </c:if>
            <c:if test="${not empty requestScope.addresses}">
                <tr class="floating-row-s">
                    <td>Місто</td>
                    <td>Вулиця</td>
                    <td>Будинок</td>
                    <td>Квартира</td>
                </tr>
            </c:if>
            <c:forEach items="${requestScope.addresses}" var="a">
                <tr class="floating-row-clickable" onclick="document.forms['redirectForm'].submit();">
                    <td>
                            ${a.city}
                        <input type="hidden" name="command"
                               value="${CommandName.COMMAND__SET_ORDER_ADDRESS}" form="redirectForm">
                        <input type="hidden" name="addressId" value="${a.id}" form="redirectForm">
                    </td>
                    <td>${a.street}</td>
                    <td>${a.houseNumber}</td>

                    <td>
                        <c:if test="${a.apartmentNumber != null}">
                            ${a.apartmentNumber}
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <tr class="floating-row">
                <td <c:if test="${not empty requestScope.addresses}"> colspan="4" </c:if>>
                    <form action="controller">
                        <button class="floating-button" name="command"
                                value="${CommandName.COMMAND__SHOW_NEW_ADDRESS}">
                            <input type="hidden" name="resp" value="order">
                            Нова адреса
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