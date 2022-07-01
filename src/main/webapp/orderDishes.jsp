<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="main.db.dao.DishesSorting" %>
<%@ page import="main.db.entities.Category" %>
<%@ page import="main.commands.CommandName" %>
<%@page import="main.Path" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>final</title>
    <link href="css/startStyles.css" rel="stylesheet" type="text/css">
    <script src="scripts/script.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#slider-range" ).slider({
                range: true,
                min: ${requestScope.prices[0]},
                max: ${requestScope.prices[1]},
                values: [ ${requestScope.prices[0]}, ${requestScope.prices[1]} ],
                slide: function( event, ui ) {
                    $( "#amount" ).val( "UAH " + ui.values[ 0 ] + " - UAH " + ui.values[ 1 ] );
                }
            });
            $( "#amount" ).val( "UAH " + $( "#slider-range" ).slider( "values", 0 ) +
                " - UAH " + $( "#slider-range" ).slider( "values", 1 ) );
        } );
    </script>
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
        <form id="dishForm" action="controller" method="post">
            <input type="hidden" name="command" value="${CommandName.COMMAND__ORDER_DISHES}"/>
            <table>
                <tr class="floating-row">
                    <td colspan="3">
                        <label>
                            <select name="sorting" form="filterForm">
                                <option value="${DishesSorting.SORT__PRICE_DESC}">Від дорожчих</option>
                                <option value="${DishesSorting.SORT__PRICE_ASC}">Від дешевих</option>
                                <option value="${DishesSorting.SORT__BY_ALPHABET}">За алфавітом</option>
                                <option value="${DishesSorting.SORT__BY_CATEGORY}">За категорією</option>
                            </select>
                        </label>
                    </td>
                    <td colspan="3">
                        <p>
                            <label for="amount">Діапазон ціни:</label>
                            <input type="text" id="amount" name="price" form="filterForm" readonly>
                        </p>
                        <div id="slider-range"></div>
                    </td>
                    <td colspan="3">
                        <div class="dropdown">
                            <button class="dropbtn">Категорії</button>
                            <div class="dropdown-content">
                                <c:forEach items="${Category.values()}" var="c">
                                    <label>
                                        <input type="checkbox" name="category" form="filterForm"
                                               value="${c.ordinal()}">${c.categoryName}
                                    </label><br>
                                </c:forEach>
                            </div>
                        </div>
                    </td>
                    <td colspan="3">
                        <form id="filterForm" action="controller">
                            <input type="hidden" name="command" value="${CommandName.COMMAND__SHOW_ORDER_DISHES}">
                            <button class="floating-button">Застосувати</button>
                        </form>
                    </td>
                </tr>
                <c:forEach items="${requestScope.dishes}" var="d" varStatus="loop">
                <tr class="floating-row-l">
                    <td colspan="4"><img src="data:image/jpeg;base64, ${requestScope.photos.get(loop.index).pic64}" class="image" alt="Food image"></td>
                    <td colspan="2">${d.name}</td>
                    <td colspan="2">${d.price} UAH</td>
                    <td colspan="2">${d.categoryId}</td>
                    <td colspan="2">
                        <input type="hidden" name="dish_id" value="${d.id}"/>
                        <div class="counter">
                            <div class="arrow-up" onclick="increment('meals-count${d.id}')"></div>
                            <label for="meals-count${d.id}"></label><input name="dish_amount" id="meals-count${d.id}" class="number-input" type="number" value="0">
                            <div class="arrow-down" onclick="decrement('meals-count${d.id}')"></div>
                        </div>
                    </td>
                </tr>
                </c:forEach>
                <tr class="floating-row">
                    <td colspan="12">
                        <input class="floating-button" type="submit" value="Вибрати адресу">
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