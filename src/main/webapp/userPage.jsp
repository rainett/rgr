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
            response.sendRedirect("login.jsp");
        }
    %>

    <div id="header">
        <a href="http://localhost:8080/final/" class="logo" id="soloLogo">dlvr.</a>
    </div>

    <div class="floatingMenu">
            <table>
                <tr><td><button class="inFormButton">Адреси доставки</button><br></td></tr>
                <tr><td><button class="inFormButton">Способи оплати</button></td></tr>
                <tr><td>
                    <form action="logout">
                        <input type="submit" value="Вийти" id="logoutButton">
                    </form>
                </td></tr>
            </table>
    </div>
</body>
</html>