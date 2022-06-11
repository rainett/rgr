<%@ page import="main.Path" %>
<%@ page import="main.db.entities.Payment" %>
<%@ page import="java.util.List" %>
<%@ page import="main.db.dao.PaymentDAO" %>
<%@ page import="main.db.entities.Order" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ресторан</title>
    <link href="css/startStyles.css" rel="stylesheet" type="text/css">
</head>
<body>

    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        if (session.getAttribute("username") == null) {
            response.sendRedirect(Path.PAGE__LOGIN);
        } else if (session.getAttribute("order") == null) {
            response.sendRedirect(Path.PAGE__ORDER_MEALS);
        }

        Order order = (Order) session.getAttribute("order");
        if (order.getCardId() == 0)
            order.setCardId(Integer.parseInt(request.getParameter("cardId")));
        session.setAttribute("order", order);

    %>

    <div id="header">
        <table>
            <tr><td><a href="<%=Path.PAGE__START%>" class="logo" id="soloLogo">dlvr.</a></td></tr>
        </table>
    </div>

    <div class="floatingMenu" id="floatingMeals">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="confirmOrder"/>
            <table>
                <tr class="mealsRow">
                    <td colspan="2">
                        Підтверджуєте замовлення на суму ${order.price}?
                    </td>
                </tr>
                <tr class="mealsRow">
                    <td><input style="width: 80%" id="logoutButton" type="submit" value="Вийти" form="cancelForm"></td>
                    <td><input style="width: 80%" id="submitButton" type="submit" value="Зберегти"></td>
                </tr>
            </table>
        </form>
        <form id="cancelForm" action="controller" method="post">
            <input type="hidden" name="command" value="cancelOrder"/>
        </form>
    </div>
</body>
</html>