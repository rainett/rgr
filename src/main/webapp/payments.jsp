<%@ page import="main.Path" %>
<%@ page import="main.db.entities.Payment" %>
<%@ page import="java.util.List" %>
<%@ page import="main.db.dao.PaymentDAO" %>
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
        List<Payment> payments = new PaymentDAO().getUserPayments((String)session.getAttribute("username"));
    %>

    <div id="header">
        <table>
            <tr><td><a href="<%=Path.PAGE__START%>" class="logo" id="soloLogo">dlvr.</a></td></tr>
        </table>
    </div>

    <div class="floatingMenu" id="floatingMeals">
        <table>
            <%  if (payments.size() == 0) { %>
            <tr style="height: 20vh">
                <td>Ви ще не додали жодної карти!</td>
            </tr>
            <%  } else { %>
            <tr class="addressNamingRow">
                <td>Номер</td>
                <td>Дійсна до</td>
            </tr>
            <%  }
            for (Payment p : payments) {
            %>
            <tr class="addressRow"
                onclick="window.location.href = '<%=Path.PAGE__UPDATE_PAYMENT%>?cardId=<%=p.getCardId()%>';">
                <td><%= p.getNumber().substring(0, p.getNumber().length()/2)+"********" %></td>
                <td><%= p.getTill().substring(0, p.getTill().length()/2)+"***" %></td>
            </tr>
            <%  } %>
            <tr style="height: 20vh">
                <td <% if (payments.size() != 0) { %> colspan="2" <% } %> >
                    <button class="inFormButton" onclick="window.location.href='<%=Path.PAGE__NEW_PAYMENT%>'">
                        Нова картка
                    </button>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>