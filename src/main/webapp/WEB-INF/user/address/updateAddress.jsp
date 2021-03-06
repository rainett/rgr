<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="main.Path" %>
<%@page import="main.commands.CommandName" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Оновити адресу" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>

<%@include file="/WEB-INF/jspf/header.jspf"%>

    <div class="floating-div">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandName.COMMAND__UPDATE_ADDRESS}"/>
            <input type="hidden" name="addressId" value="${requestScope.address.id}"/>
            <table>
                <tr class="floating-row">
                    <td>
                        <label>
                            <input class="floating-input" type="text" name="city" value="${requestScope.address.city}" placeholder="City" required>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input class="floating-input" type="text" name="street" value="${requestScope.address.street}" placeholder="Street" required>
                        </label>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td>
                        <label>
                            <input class="floating-input" type="text" name="houseNumber" value="${requestScope.address.houseNumber}" placeholder="House number" required>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input class="floating-input" type="text" name="apartmentNumber" value="${requestScope.address.apartmentNumber == null ? "" : requestScope.address.apartmentNumber}" placeholder="Apartment number">
                        </label>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="2"><input class="floating-button" type="submit" value="Зберегти"></td>
                </tr>
                <tr class="floating-row">
                    <td colspan="2">
                        <input class="floating-button-danger" type="submit" value="Видалити адресу" form="deleteAddress">
                    </td>
                </tr>
            </table>
        </form>
        <form id="deleteAddress" action="controller" method="post">
            <input type="hidden" name="command" value="deleteAddress"/>
            <input type="hidden" name="addressId" value="${requestScope.address.id}"/>
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