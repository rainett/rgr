<%@ page import="main.Path" %>
<%@ page import="main.db.entities.Dish" %>
<%@ page import="java.util.List" %>
<%@ page import="main.db.dao.DishesDAO" %>
<%@ page import="main.db.entities.Order" %>
<%@ page import="main.db.dao.OrderDAO" %>
<%@ page import="main.db.dao.OrderedDishesDAO" %>
<%@ page import="main.db.entities.OrderedDish" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ресторан</title>
    <link href="css/startStyles.css" rel="stylesheet" type="text/css">
</head>
<body>

<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect(Path.PAGE__LOGIN);
    }
    Order order = new OrderDAO().getOrder(Integer.parseInt(request.getParameter("orderId")));
    List<OrderedDish> orderedDishes = new OrderedDishesDAO().getOrderedDishes(order.getOrderedId());
    DishesDAO dishesDAO = new DishesDAO();
    List<Dish> dishes = new ArrayList<>();
    orderedDishes.forEach(o -> dishes.add(dishesDAO.findDish(o.getDishId())));
%>


    <div id="header">
        <table>
            <tr><td><a href="<%=Path.PAGE__START%>" class="logo" id="soloLogo">dlvr.</a></td></tr>
        </table>
    </div>

    <div class="floatingMenu" id="floatingMeals">
        <table class="tableClass">
            <tr class="addressNamingRow">
                <td></td>
                <td colspan="2">Замовлення No:<%=order.getOrderId()%></td>
                <td colspan="2">Ціна <%=order.getPrice()%></td>
                <td></td>
            </tr>
            <%
                Dish d;
                for (int i = 0; i < orderedDishes.size(); i++) {
                    d = dishes.get(i);
            %>
            <tr class="mealsRow">
                <td colspan="2"><img src="<%=d.getPic()%>" class="images" alt="Food image"></td>
                <td><%=d.getName()%></td>
                <td><%=d.getPrice()%> UAH</td>
                <td colspan="2">Кількість: <%=orderedDishes.get(i).getDishAmount()%></td>
            </tr>
            <%
                }
            %>
            <tr style="height: 20vh">
                <td></td>
                <td colspan="4">
                    <input style="width: 80%" type="submit" value="Відмінити замовлення" id="logoutButton" form="deleteAddress">
                </td>
                <td></td>
            </tr>
        </table>
        <form id="deleteAddress" action="controller" method="post">
            <input type="hidden" name="command" value="cancelOrder"/>
            <input type="hidden" name="orderId" value="<%=order.getOrderId()%>"/>
        </form>
    </div>
</body>
</html>