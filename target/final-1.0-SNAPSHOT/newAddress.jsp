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
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        if (session.getAttribute("username") == null) {
            response.sendRedirect(Path.PAGE__LOGIN);
        }
    %>

    <div id="header">
        <table>
            <tr><td><a href="<%=Path.PAGE__START%>" class="logo" id="soloLogo">dlvr.</a></td></tr>
        </table>
    </div>

    <div class="floatingMenu">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="newAddress"/>
            <table>
                <tr>
                    <td><input class="address-input" type="text" name="city" placeholder="City" required></td>
                    <td><input class="address-input" type="text" name="street" placeholder="Street" required></td>
                </tr>
                <tr>
                    <td><input class="address-input" type="text" name="houseNumber" placeholder="House number" required></td>
                    <td><input class="address-input" type="text" name="apartmentNumber" placeholder="Apartment number"></td>
                </tr>
                <tr>
                    <td colspan="2"><input id="submitButton" type="submit" value="Зберегти"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>