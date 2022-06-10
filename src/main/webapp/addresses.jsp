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
                Address a;
                for (int i = 0; i <addresses.size(); i++) {
                    a = addresses.get(i);
            %>
            <tr class="addressRow" onclick="window.location.href = '<%=Path.PAGE__UPDATE_ADDRESS%>?addressId=<%=a.getAddressId()%>';">
                <td <% if (i == addresses.size() - 1) {%> class="last-left-cell" <%}%>><%=a.getCity()%></td>
                <td><%=a.getStreet()%></td>
                <td><%=a.getHouseNumber()%></td>

                <td <% if (i == addresses.size() - 1) {%> class="last-right-cell" <%}%>>
                    <%  if (a.getApartmentNumber() != null) {   %>
                    <%=a.getApartmentNumber()%>
                    <%  }   %>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</body>
</html>