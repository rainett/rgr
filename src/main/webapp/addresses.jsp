<%@ page import="main.Path" %>
<%@ page import="java.util.List" %>
<%@ page import="main.db.entities.Address" %>
<%@ page import="main.db.dao.AddressDAO" %>
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

        List<Address> addresses = new AddressDAO().getUserAddresses((String)session.getAttribute("username"));
    %>

    <div id="header">
        <table>
            <tr><td><a href="<%=Path.PAGE__START%>" class="logo" id="soloLogo">dlvr.</a></td></tr>
        </table>
    </div>

    <div class="floatingMenu" id="floatingMeals">
        <table>
            <tr class="addressNamingRow">
                <td>Місто</td>
                <td>Вулиця</td>
                <td>Будинок</td>
                <td>Квартира</td>
            </tr>
            <%
                for (Address a : addresses) {
            %>
            <tr class="addressRow">
                <td><%=a.getCity()%></td>
                <td><%=a.getStreet()%></td>
                <td><%=a.getHouseNumber()%></td>
                <%
                    if (a.getApartmentNumber() != null) {
                %>
                <td><%=a.getApartmentNumber()%></td>
                <%
                    }
                %>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>