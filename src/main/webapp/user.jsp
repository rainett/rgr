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
            response.sendRedirect("login.jsp");
        }
    %>

    <div id="header">
        <table>
            <tr><td><a href="<%=Path.PAGE__START%>" class="logo" id="soloLogo">dlvr.</a></td></tr>
        </table>
    </div>

    <div class="floatingMenu">
        <table>
            <tr><td><button class="inFormButton" onclick="document.location.href='<%=Path.PAGE__ADDRESSES%>'">Адреси доставки</button><br></td></tr>
            <tr><td><button class="inFormButton" onclick="document.location.href='<%=Path.PAGE__PAYMENTS%>'">Способи оплати</button></td></tr>
            <tr><td>
                <form action="controller">
                    <input type="hidden" name="command" value="logout"/>
                    <input type="submit" value="Вийти" id="logoutButton">
                </form>
            </td></tr>
        </table>
    </div>
</body>
</html>