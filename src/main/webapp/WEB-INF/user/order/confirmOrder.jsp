<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="main.Path" %>
<%@page import="main.commands.CommandName" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Підтвердити замовлення" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>

<%@include file="/WEB-INF/jspf/header.jspf"%>

    <div class="floating-div">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandName.COMMAND__CONFIRM_ORDER}"/>
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
            <input type="hidden" name="command" value="${CommandName.COMMAND__CANCEL_ORDER}"/>
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