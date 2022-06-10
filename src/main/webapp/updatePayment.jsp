<%@ page import="main.Path" %>
<%@ page import="main.db.dao.UserDAO" %>
<%@ page import="main.db.dao.PaymentDAO" %>
<%@ page import="main.db.entities.Payment" %>
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
        PaymentDAO paymentDAO = new PaymentDAO();
        long cardId = Long.parseLong(request.getParameter("cardId"));
        if (session.getAttribute("username") == null || new UserDAO().findUser((String) session.getAttribute("username")).getClientId() != paymentDAO.findPayment(cardId).getClientId()) {
            response.sendRedirect("login.jsp");
        }
        Payment payment = paymentDAO.findPayment(cardId);
    %>

    <div id="header">
        <table>
            <tr><td><a href="<%=Path.PAGE__START%>" class="logo" id="soloLogo">dlvr.</a></td></tr>
        </table>
    </div>

    <div class="floatingMenu">
        <form action="controller" method="post" onsubmit="return validateCard()">
            <table>
                <tr>
                    <input type="hidden" name="command" value="updatePayment"/>
                    <input type="hidden" name="cardId" value="<%=cardId%>"/>
                    <td colspan="2"><input style="width: 90%" class="address-input" id="numberInput" type="text" name="number" value="<%=payment.getNumber()%>" placeholder="Number" required></td>
                </tr>
                <tr>
                    <td><input class="address-input" id="tillInput" type="text" name="till" value="<%=payment.getTill()%>" placeholder="Expiration date" required></td>
                    <td><input class="address-input" id="cvvInput"  type="password" name="cvv" value="<%=payment.getCvv()%>" placeholder="CVV" required></td>
                </tr>
                <tr>
                    <td colspan="2"><input id="submitButton" type="submit" value="Зберегти"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Видалити картку" id="logoutButton" form="deleteCard">
                    </td>
                </tr>
            </table>
        </form>
        <form id="deleteCard" action="controller" method="post">
            <input type="hidden" name="command" value="deletePayment"/>
            <input type="hidden" name="cardId" value="<%=cardId%>"/>
        </form>
    </div>
</body>
</html>