<%@ page import="main.Path" %>
<%@ page import="java.util.List" %>
<%@ page import="main.db.entities.Address" %>
<%@ page import="main.db.dao.AddressDAO" %>
<%@ page import="main.db.entities.Order" %>
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
        } else if (session.getAttribute("order") == null) {
            response.sendRedirect(Path.PAGE__ORDER_MEALS);
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
            <%  if (addresses.size() == 0) { %>
            <tr style="height: 20vh">
                <td>Ви ще не додали жодної адреси!</td>
            </tr>
            <%  } else { %>
            <tr class="addressNamingRow">
                <td>Місто</td>
                <td>Вулиця</td>
                <td>Будинок</td>
                <td>Квартира</td>
            </tr>
            <%  }
                for (Address a : addresses) {
            %>
            <tr class="addressRow"
                onclick="window.location.href = '<%=Path.PAGE__ORDER_PAYMENTS%>?addressId=<%=a.getAddressId()%>';">
                <td><%=a.getCity()%></td>
                <td><%=a.getStreet()%></td>
                <td><%=a.getHouseNumber()%></td>

                <td>
                    <% if (a.getApartmentNumber() != null) { %>
                    <%=a.getApartmentNumber()%>
                    <% } %>
                </td>
            </tr>
            <%  } %>
            <tr style="height: 20vh">
                <td <% if (addresses.size() != 0) { %> colspan="4" <% } %> >
                    <button class="inFormButton" onclick="window.location.href='<%=Path.PAGE__NEW_ADDRESS%>'">
                        Нова адреса
                    </button>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>