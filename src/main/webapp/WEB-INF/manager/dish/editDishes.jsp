<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="main.db.dao.DishesSorting" %>
<%@ page import="main.db.entities.Category" %>
<%@ page import="main.commands.CommandName" %>
<%@page import="main.Path" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Редагувати страви" scope="page"/>
<%@ include file="/WEB-INF/jspf/sliderHead.jspf" %>
<body>

<%@include file="/WEB-INF/jspf/header.jspf"%>

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
                        <input type="hidden" name="command" value="${CommandName.COMMAND__SHOW_EDIT_DISHES}">
                        <button class="floating-button">Застосувати</button>
                    </form>
                </td>
            </tr>
            <tr class="floating-row">
                <td colspan="20">
                    <form action="controller">
                        <button class="floating-button" name="command"
                                value="${CommandName.COMMAND__SHOW_NEW_DISH}">
                            Нова страва
                        </button>
                    </form>
                </td>
            </tr>
            <c:forEach items="${requestScope.dishes}" var="d" varStatus="loop">
                <tr class="floating-row-l-clickable" onclick="document.forms['updateForm${d.id}'].submit();" >
                    <td colspan="8">
                        <form action="controller" id="updateForm${d.id}">
                            <img src="data:image/jpeg;base64, ${requestScope.photos.get(loop.index).pic64}"
                                 class="image" alt="Food image">
                            <input type="hidden" name="command"
                                   value="${CommandName.COMMAND__SHOW_UPDATE_DISH}">
                            <input type="hidden" name="dishId"
                                   value="${d.id}">
                        </form>
                    </td>
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