<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="main.commands.CommandName" %>
<%@page import="main.Path" %>
<%@page import="main.db.entities.Category" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>final</title>
    <link href="css/startStyles.css" rel="stylesheet" type="text/css">
</head>
<body>

    <div id="header">
        <table>
            <tr><td><a href="${Path.PAGE__START}" class="logo">dlvr.</a></td></tr>
        </table>
    </div>

    <div class="floating-div">
        <form action="controller" method="post" enctype="multipart/form-data">
            <input type="hidden" name="command" value="${CommandName.COMMAND__UPDATE_DISH}">
            <input type="hidden" name="dishId" value="${requestScope.dish.id}">
            <table>
                <tr class="floating-row">
                    <td>
                        <label>
                            <input type="text" class="floating-input" name="dishName" placeholder="Name"
                                   value="${requestScope.dish.name}" required>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input type="number" class="floating-input" name="dishPrice" placeholder="Price"
                                   value="${requestScope.dish.price}" required>
                        </label>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td>
                        <label class="custom-file-upload">
                            Оновіть зображення
                            <input type="file" class="floating-input" name="dishPic" accept=".jpeg,.jpg,.png">
                        </label>
                    </td>
                    <td>
                        <label>
                            <select name="dishCategory">
                                <option value="${requestScope.dish.category}" selected disabled>
                                    ${requestScope.dish.category}
                                </option>
                                <c:forEach items="${Category.values()}" var="c">
                                    <option value="${c.categoryName}">${c.categoryName}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="2">
                        <label>
                            <input type="submit" class="floating-button" value="Підтвердити">
                        </label>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="2">
                        <label>
                            <input type="submit" form="deleteForm" class="floating-button-danger"
                                   value="Видалити страву">
                        </label>
                    </td>
                </tr>
            </table>
        </form>
        <form id="deleteForm" action="controller" method="post">
            <input type="hidden" name="command" value="${CommandName.COMMAND__DELETE_DISH}">
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