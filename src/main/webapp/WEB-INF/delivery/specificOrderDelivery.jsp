<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="main.Path" %>
<%@page import="main.db.entities.OrderState" %>
<%@page import="main.commands.CommandName" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Замовлення" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>

<%@include file="/WEB-INF/jspf/header.jspf"%>

    <div class="floating-div">
        <table>
            <tr class="floating-row-s">
                <td colspan="20">Замовлення №:${requestScope.order.id}</td>
                <td colspan="20">Ціна: ${requestScope.order.price}UAH</td>
                <td colspan="20">Стан: ${OrderState.getState(requestScope.order.stateId).stateName}</td>
            </tr>
            <tr class="floating-row-s">
                <td colspan="${requestScope.address.apartmentNumber != null ? 15 : 20}">
                    Місто
                </td>
                <td colspan="${requestScope.address.apartmentNumber != null ? 15 : 20}">
                    Вулиця
                </td>
                <td colspan="${requestScope.address.apartmentNumber != null ? 15 : 20}">
                    Будинок
                </td>
                <c:if test="${requestScope.address.apartmentNumber != null}">
                    <td colspan="15">Квартира</td>
                </c:if>
            </tr>
            <tr class="floating-row-s">
                <td colspan="${requestScope.address.apartmentNumber != null ? 15 : 20}">
                    ${requestScope.address.city}
                </td>
                <td colspan="${requestScope.address.apartmentNumber != null ? 15 : 20}">
                    ${requestScope.address.street}
                </td>
                <td colspan="${requestScope.address.apartmentNumber != null ? 15 : 20}">
                    ${requestScope.address.houseNumber}
                </td>
                <c:if test="${requestScope.address.apartmentNumber != null}">
                    <td colspan="15">${requestScope.address.apartmentNumber}</td>
                </c:if>
            </tr>
            <c:forEach items="${requestScope.dishes}" var="d" varStatus="loop">
            <tr class="floating-row-l">
                <td colspan="24"><img src="data:image/jpeg;base64, ${requestScope.photos.get(loop.index).pic64}" class="image" alt="Food image"></td>
                <td colspan="12">${d.name}</td>
                <td colspan="12">${d.price} UAH</td>
                <td colspan="12">Кількість: ${requestScope.orderedDishes.get(loop.index).dishAmount}</td>
            </tr>
            </c:forEach>
            <c:if test="${requestScope.order.stateId == OrderState.PRE_DELIVERING.ordinal()}">
                <tr class="floating-row">
                    <td colspan="60">
                        <input class="floating-button" type="submit" value="Прийняти на доставку" form="acceptToCook">
                    </td>
                </tr>
            </c:if>
            <c:if test="${requestScope.order.stateId == OrderState.DELIVERING.ordinal()}">
                <tr class="floating-row">
                    <td colspan="60">
                        <input class="floating-button" type="submit" value="Доставлено" form="cooked">
                    </td>
                </tr>
            </c:if>
        </table>
        <form id="acceptToCook" action="controller" method="post">
            <input type="hidden" name="command" value="${CommandName.COMMAND__ORDER_DELIVERING}"/>
            <input type="hidden" name="orderId" value="${requestScope.order.id}"/>
        </form>
        <form id="cooked" action="controller" method="post">
            <input type="hidden" name="command" value="${CommandName.COMMAND__ORDER_DELIVERED}"/>
            <input type="hidden" name="orderId" value="${requestScope.order.id}"/>
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