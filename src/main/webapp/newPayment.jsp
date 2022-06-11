<%@ page import="main.Path" %>
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
        <form action="controller" method="post" onsubmit="return validateCard()">
            <input type="hidden" name="command" value="newPayment"/>
            <table>
                <tr>
                    <td colspan="2"><input style="width: 90%" id="numberInput" class="address-input" type="text" name="number" placeholder="Number" required></td>
                </tr>
                <tr>
                    <td><input class="address-input" id="tillInput" type="text" name="till" placeholder="Expiration date" required></td>
                    <td><input class="address-input" id="cvvInput" type="password" name="cvv" placeholder="CVV" required></td>
                </tr>
                <tr>
                    <td colspan="2"><input id="submitButton" type="submit" value="Зберегти"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>