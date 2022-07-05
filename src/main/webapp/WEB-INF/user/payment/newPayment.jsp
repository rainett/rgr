<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="main.Path" %>
<%@page import="main.commands.CommandName" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Новий спосіб оплати" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>

<%@include file="/WEB-INF/jspf/header.jspf"%>

    <div class="floating-div">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandName.COMMAND__NEW_PAYMENT}"/>
            <input type="hidden" name="resp" value="${requestScope.resp}"/>
            <table>
                <tr class="floating-row">
                    <td colspan="2">
                        <label>
                            <input class="floating-input" type="text" name="number" pattern="^[0-9]{16}$"
                                   placeholder="Number" required>
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
                            <input class="floating-input" type="text" pattern="(0[1-9]|1[0-2])/[0-9]{2}"
                                   name="till" placeholder="Expiration date" required>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input class="floating-input" type="password" name="cvv" pattern="^[0-9]{3}$"
                                   placeholder="CVV" required>
                        </label>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="2">
                        <input class="floating-button" type="submit" value="Зберегти">
                    </td>
                </tr>
            </table>
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