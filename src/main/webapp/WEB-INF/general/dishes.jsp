<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="main.db.dao.DishesSorting" %>
<%@ page import="main.commands.CommandName" %>
<%@page import="main.Path" %>
<%@page import="main.db.entities.Category" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Страви" scope="page"/>
<%@ include file="/WEB-INF/jspf/sliderHead.jspf" %>
<body>

    <div id="header">
        <table>
            <tr><td><a href="${Path.PAGE__START}" class="logo">dlvr.</a></td></tr>
        </table>
    </div>

    <div class="floating-div">
        <table>
            <tr class="floating-row">
                <td colspan="5">
                    <label>
                        <select name="sorting" form="filterForm">
                            <option value="${DishesSorting.SORT__PRICE_DESC}">Від дорожчих</option>
                            <option value="${DishesSorting.SORT__PRICE_ASC}">Від дешевих</option>
                            <option value="${DishesSorting.SORT__BY_ALPHABET}">За алфавітом</option>
                            <option value="${DishesSorting.SORT__BY_CATEGORY}">За категорією</option>
                        </select>
                    </label>
                </td>
                <td colspan="5">
                    <p>
                        <label for="amount">Діапазон ціни:</label>
                        <input type="text" id="amount" name="price" form="filterForm" readonly>
                    </p>
                    <div id="slider-range"></div>
                </td>
                <td colspan="5">
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
                <td colspan="5">
                    <form id="filterForm" action="controller">
                        <input type="hidden" name="command" value="${CommandName.COMMAND__DISHES}">
                        <button class="floating-button">Застосувати</button>
                    </form>
                </td>
            </tr>
            <c:forEach items="${requestScope.dishes}" var="d" varStatus="loop">
                <tr class="floating-row-l">
                    <td colspan="8"><img src="data:image/jpeg;base64, ${requestScope.photos.get(loop.index).pic64}" class="image" alt="Food image"></td>
                    <td colspan="4">${d.name}</td>
                    <td colspan="4">${d.price} UAH</td>
                    <td colspan="4">${Category.getCategory(d.categoryId).categoryName}</td>
                </tr>
            </c:forEach>
            <tr class="floating-row-s"></tr>
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