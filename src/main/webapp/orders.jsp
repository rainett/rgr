<%@ page import="main.Path" %>
<%@ page import="java.util.List" %>
<%@ page import="main.db.entities.Address" %>
<%@ page import="main.db.entities.Order" %>
<%@ page import="main.db.dao.OrderDAO" %>
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
        }

        List<Order> orders = new OrderDAO().getUserOrders((String)session.getAttribute("username"));
    %>

    <div id="header">
        <table>
            <tr><td><a href="<%=Path.PAGE__START%>" class="logo" id="soloLogo">dlvr.</a></td></tr>
        </table>
    </div>

    <div class="floatingMenu" id="floatingMeals">
        <table>
            <%  if (orders.size() == 0) { %>
            <tr style="height: 20vh">
                <td>У вас ще нема активних замовлень</td>
            </tr>
            <%  } else { %>
            <tr class="addressNamingRow">
                <td>Номер замовлення</td>
                <td>Сума замовлення</td>
            </tr>
            <%  }
                for (Order o : orders) {
            %>
            <tr class="addressRow"
                onclick="window.location.href = '<%=Path.PAGE__SPECIFIC_ORDER%>?orderId=<%=o.getOrderId()%>'">
                <td><%=o.getOrderId()%></td>
                <td><%=o.getPrice()%></td>
            </tr>
            <%  } %>
            <tr style="height: 20vh">
                <td <% if (orders.size() != 0) { %> colspan="2" <% } %> >
                    <button class="inFormButton" onclick="window.location.href='<%=Path.PAGE__ORDER_MEALS%>'">
                        Нове замовлення
                    </button>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>