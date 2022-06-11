<%@ page import="main.Path" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ресторан</title>
    <link href="css/startStyles.css" rel="stylesheet" type="text/css">
</head>
<body>

    <%
        String loginButtonText = "Увійти";
        if (session.getAttribute("username") != null) {
            loginButtonText = String.format("Привіт, %s!", session.getAttribute("username"));
        }
    %>

    <div id="header">
        <table>
            <tr>
                <td><a href="<%=Path.PAGE__START%>" class="logo">dlvr.</a></td>
                <td><button id="meals" onclick="document.location.href='<%=Path.PAGE__MEALS%>'">Меню страв</button></td>
                <td><button id="login" onclick="document.location.href='<%=Path.PAGE__LOGIN%>'"><%=loginButtonText%></button></td>
            </tr>
        </table>
    </div>

    <button id="make-order-btn" onclick="document.location.href='<%=Path.PAGE__ORDER_MEALS%>'">Оформити замовлення</button>
</body>
</html>