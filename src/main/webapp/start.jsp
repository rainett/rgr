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
        <tr  >
            <td><a href="<%=Path.PAGE__START%>" class="logo">dlvr.</a></td>
            <td><button id="meals" onclick="document.location.href='<%=Path.PAGE__MEALS%>'">Меню страв</button></td>
            <td><button id="make_ord" onclick="document.location.href='<%=Path.PAGE__ORDER_MEALS%>'">Оформити замовлення</button></td>
            <td><button id="login" onclick="document.location.href='<%=Path.PAGE__LOGIN%>'"><%=loginButtonText%></button></td>
        </tr>
    </table>

</div>

<div id="floatingMeals">
    <div id="main-about-us">
        <table style="height: 20vh">
            <tr>
                <td>
                    <img style="height: 30vh" src="images/delivery-van.png">
                </td>
            </tr>
            <tr style="height: 5vh">
                <td>
                    Доставка їжі та не тільки!
                </td>
            </tr>
            <tr style="height: 20vh">
                <td>

                </td>
            </tr>
            <tr>
                <td>
                    <img style="height: 30vh" src="images/places.png">
                </td>
            </tr>
            <tr style="height: 5vh">
                <td>
                    У будь-яке зручне для вас місце
                </td>
            </tr>
            <tr style="height: 10vh">
                <td>

                </td>
            </tr>
        </table>
    </div>
</div>

<table style="height: 50vh">
    <tr>
        <td>
        </td>
    </tr>
</table>

<footer class="footer">

    <div class="row">
        <div class="column-left">
            <div id="center">
                м.Львів вул.Куліша 5а
                <br>+380(063)32-432-432
            </div>
        </div>
    </div>

    <div class="column-right">
        <table>
            <tr>
                <td><img src="images/footer/instagram.png" style="width: 30px"> </td>
                <td><img src="images/footer/twitter.png" style="width: 30px"> </td>
                <td><img src="images/footer/facebook.png" style="width: 30px"> </td>
            </tr>
        </table>
    </div>

    <div class="column-center">
        <table>
            <tr>
                <td><a class="color" href="meals.jsp">Меню</a> </td>
                <td><a class="color" href="orderMeals.jsp">Оформити замовлення</a> </td>
                <td><a class="color" href="login.jsp">Увійти</a> </td>
            </tr>
        </table>
    </div>
</footer>

</body>
</html>