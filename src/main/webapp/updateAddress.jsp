<%@ page import="main.Path" %>
<%@ page import="main.db.entities.Address" %>
<%@ page import="main.db.dao.AddressDAO" %>
<%@ page import="main.db.dao.UserDAO" %>
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
        AddressDAO addressDAO = new AddressDAO();
        long addressId = Long.parseLong(request.getParameter("addressId"));
        if (session.getAttribute("username") == null || new UserDAO().findUser((String) session.getAttribute("username")).getClientId() != addressDAO.findAddress(addressId).getClientId()) {
            response.sendRedirect(Path.PAGE__LOGIN);
        }
        Address address = addressDAO.findAddress(addressId);
    %>

    <div id="header">
        <table>
            <tr><td><a href="<%=Path.PAGE__START%>" class="logo" id="soloLogo">dlvr.</a></td></tr>
        </table>
    </div>

    <div class="floatingMenu">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="updateAddress"/>
            <input type="hidden" name="addressId" value="<%=addressId%>"/>
            <table>
                <tr>
                    <td><input class="address-input" type="text" name="city" value="<%=address.getCity()%>" placeholder="City" required></td>
                    <td><input class="address-input" type="text" name="street" value="<%=address.getStreet()%>" placeholder="Street" required></td>
                </tr>
                <tr>
                    <td><input class="address-input" type="text" name="houseNumber" value="<%=address.getHouseNumber()%>" placeholder="House number" required></td>
                    <td><input class="address-input" type="text" name="apartmentNumber" value="<%=address.getApartmentNumber() == null ? "" : address.getApartmentNumber()%>" placeholder="Apartment number"></td>
                </tr>
                <tr>
                    <td colspan="2"><input id="submitButton" type="submit" value="Зберегти"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Видалити адресу" id="logoutButton" form="deleteAddress">
                    </td>
                </tr>
            </table>
        </form>
        <form id="deleteAddress" action="controller" method="post">
            <input type="hidden" name="command" value="deleteAddress"/>
            <input type="hidden" name="addressId" value="<%=addressId%>"/>
        </form>
    </div>
</body>
</html>