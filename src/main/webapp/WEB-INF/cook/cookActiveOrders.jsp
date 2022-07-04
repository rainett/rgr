<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="main.commands.CommandName" %>
<%@page import="main.Path" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <table>
            <c:if test="${empty requestScope.orders}">
                <tr class="floating-row-l">
                    <td>Активних замовлень нема</td>
                </tr>
            </c:if>
            <c:if test="${not empty requestScope.orders}">
                <tr class="floating-row-s">
                    <td>Номер замовлення</td>
                    <td>Сума замовлення</td>
                </tr>
            </c:if>
            <c:forEach items="${requestScope.orders}" var="o">
                <tr class="floating-row-s-clickable" onclick="document.forms['redirectForm${o.id}'].submit();">
                    <td>
                        <form action="controller" id="redirectForm${o.id}">
                            ${o.id}
                            <input type="hidden" name="command"
                                   value="${CommandName.COMMAND__SHOW_SPECIFIC_ORDER_COOK}">
                            <input type="hidden" name="orderId" value="${o.id}">
                        </form>
                    </td>
                    <td>${o.price}</td>
                </tr>
            </c:forEach>
            <tr class="floating-row"></tr>
        </table>
    </div>
    <table style="height: 30vh">
        <tr>
            <td>
            </td>
        </tr>
    </table>
</body>
</html>