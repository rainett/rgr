<%@ page import="main.commands.CommandName" %>
<%@page import="main.Path" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <c:if test="${empty requestScope.addresses}">
                <tr class="floating-row-l">
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
                <tr class="floating-row-clickable" onclick="document.forms['updateForm${a.id}'].submit();" >
                    <td>
                        <form id="updateForm${a.id}" action="controller">
                                ${a.city}
                            <input type="hidden" name="command"
                                   value="${CommandName.COMMAND__SHOW_UPDATE_ADDRESS}">
                            <input type="hidden" name="addressId"
                                   value="${a.id}">
                        </form>
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
                    <button class="floating-button" name="command"
                            value="${CommandName.COMMAND__SHOW_NEW_ADDRESS}" form="newAddressForm">
                        <input type="hidden" name="resp" value="addresses" form="newAddressForm">
                        Нова адреса
                    </button>
                </td>
            </tr>
        </table>
        <form id="newAddressForm" action="controller"></form>
    </div>
    <table style="height: 30vh">
        <tr>
            <td>
            </td>
        </tr>
    </table>
</body>
</html>