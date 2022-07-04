<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="main.commands.CommandName" %>
<%@ page import="main.Path" %>
<%@ page import="main.db.entities.Role" %>
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
                <td>${Role.getRole(sessionScope.user.roleId).roleName}</td>
            </tr>
        </table>
    </div>

    <div class="floating-div">
        <table>
            <tr class="floating-row">
                <td>
                    <button class="floating-button" name="command" form="redirectForm"
                            value="${CommandName.COMMAND__ORDERS}">Мої замовлення</button>
                </td>
            </tr>
            <tr class="floating-row">
                <td>
                    <button class="floating-button" name="command" form="redirectForm"
                            value="${CommandName.COMMAND__ADDRESSES}">Адреси доставки</button>
                </td>
            </tr>
            <tr class="floating-row">
                <td>
                    <button class="floating-button" name="command" form="redirectForm"
                            value="${CommandName.COMMAND__PAYMENTS}">Способи оплати</button>
                </td>
            </tr>
            <c:if test="${sessionScope.user.roleId == Role.MANAGER.ordinal()}">
                <tr class="floating-row">
                    <td>
                        <button class="floating-button" name="command" form="redirectForm"
                                value="${CommandName.COMMAND__SHOW_EDIT_DISHES}">Редагувати меню</button>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td>
                        <button class="floating-button" name="command" form="redirectForm"
                                value="${CommandName.COMMAND__SHOW_APPLICATIONS}">
                            Заявки на працевлаштування
                        </button>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td>
                        <button class="floating-button" name="command" form="redirectForm"
                                value="${CommandName.COMMAND__SHOW_MANAGER_ORDERS}">
                            Активні замовлення
                        </button>
                    </td>
                </tr>
            </c:if>
            <c:if test="${sessionScope.user.roleId == Role.CLIENT.ordinal()}">
                <tr class="floating-row">
                    <td>
                        <button class="floating-button" name="command" form="redirectForm"
                                value="${CommandName.COMMAND__SHOW_SEND_APPLICATION}">
                            Подати заявку на працевлаштування
                        </button>
                    </td>
                </tr>
            </c:if>
            <c:if test="${sessionScope.user.roleId == Role.COOK.ordinal()}">
                <tr class="floating-row">
                    <td>
                        <button class="floating-button" name="command" form="redirectForm"
                                value="${CommandName.COMMAND__SHOW_COOK_ORDERS}">
                            Замовлення на приготування
                        </button>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td>
                        <button class="floating-button" name="command" form="redirectForm"
                                value="${CommandName.COMMAND__SHOW_COOK_ACTIVE_ORDERS}">
                            Замовлення, що готуються
                        </button>
                    </td>
                </tr>
            </c:if>
            <c:if test="${sessionScope.user.roleId == Role.COURIER.ordinal()}">
                <tr class="floating-row">
                    <td>
                        <button class="floating-button" name="command" form="redirectForm"
                                value="${CommandName.COMMAND__SHOW_DELIVERY_ORDERS}">
                            Замовлення на доставку
                        </button>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td>
                        <button class="floating-button" name="command" form="redirectForm"
                                value="${CommandName.COMMAND__SHOW_DELIVERY_ACTIVE_ORDERS}">
                            Замовлення, що доставляються
                        </button>
                    </td>
                </tr>
            </c:if>
            <tr class="floating-row">
                <td>
                    <button class="floating-button-danger" name="command" form="redirectForm"
                            value="logout">Вийти</button>
                </td>
            </tr>
        </table>
        <form id="redirectForm" action="controller"></form>
    </div>
    <table style="height: 30vh">
        <tr>
            <td>
            </td>
        </tr>
    </table>
</body>
</html>