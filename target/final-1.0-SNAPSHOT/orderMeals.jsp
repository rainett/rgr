<%@ page import="main.Path" %>
<%@ page import="main.db.entities.Dish" %>
<%@ page import="java.util.List" %>
<%@ page import="main.db.dao.DishesDAO" %>
<%@ page import="main.db.dao.DishesSorting" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ресторан</title>
    <link href="css/startStyles.css" rel="stylesheet" type="text/css">
    <script src="scripts/script.js"></script>
</head>
<body>

<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect(Path.PAGE__LOGIN);
    }
    String currentSorting = request.getParameter("sorting");
    List<Dish> dishes = new DishesDAO().getAllDishes(currentSorting);
    String uri = request.getRequestURL()+"?sorting=";
%>


    <div id="header">
        <table>
            <tr><td><a href="<%=Path.PAGE__START%>" class="logo" id="soloLogo">dlvr.</a></td></tr>
        </table>
    </div>

    <div class="floatingMenu" id="floatingMeals">
        <form id="dishForm" action="controller" method="post">
            <input type="hidden" name="command" value="orderDishes"/>
            <table class="tableClass">
                <tr>
                    <td colspan="4">
                        <div class="dropdown">
                            <button disabled class="dropbtn">Сортувати</button>
                            <div class="dropdown-content">
                                <a href=<%=uri+ DishesSorting.SORT__FROM_EXPENSIVE%>>Від дорожчих</a>
                                <a href=<%=uri+DishesSorting.SORT__FROM_CHEAP%>>Від дешевших</a>
                                <a href=<%=uri+DishesSorting.SORT__BY_ALPHABET%>>За алфавітом</a>
                            </div>
                        </div>
                    </td>
                </tr>
                <%
                    for (Dish d : dishes) {
                %>
                <tr class="mealsRow">
                    <td><img src="<%=d.getPic()%>" class="images" alt="Food image"></td>
                    <td><%=d.getName()%></td>
                    <td><%=d.getPrice()%> UAH</td>
                    <td>
                        <input type="hidden" name="dish_id" value="<%=d.getId()%>"/>
                        <div class="counter">
                            <div class="arrow-up" onclick="increment('<%="meals-count"+d.getId()%>')"></div>
                            <input name="dish_amount" id="<%="meals-count"+d.getId()%>" class="number-input" type="number" value="0">
                            <div class="arrow-down" onclick="decrement('<%="meals-count"+d.getId()%>')"></div>
                        </div>
                    </td>
                </tr>
                <%
                    }
                %>
                <tr class="mealsRow">
                    <td colspan="4">
                        <input id="submitButton" type="submit" value="Вибрати адресу">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>