<%@page import="main.Path" %>
<%@page import="main.commands.CommandName" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>final</title>
    <link href="${pageContext.request.contextPath}/css/startStyles.css" rel="stylesheet" type="text/css">
</head>
<body>

    <div id="header">
        <table>
            <tr>
                <td><a href="${Path.PAGE__START}" class="logo">dlvr.</a></td>
                <td>
                    <form action="controller">
                        <button name="command" value="${CommandName.COMMAND__SHOW_LOGIN}">
                            Особистий кабінет
                        </button>
                    </form>
                </td>
            </tr>
        </table>
    </div>

    <div class="floating-div">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandName.COMMAND__NEW_ADDRESS}"/>
            <input type="hidden" name="resp" value="${requestScope.resp}"/>
            <table>
                <tr class="floating-row">
                    <td>
                        <label>
                            <input class="floating-input" type="text" name="city" placeholder="City" required>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input class="floating-input" type="text" name="street" placeholder="Street" required>
                        </label>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td>
                        <label>
                            <input class="floating-input" type="text" name="houseNumber" placeholder="House number" required>
                        </label>
                    </td>
                    <td>
                        <label>
                            <input class="floating-input" type="text" name="apartmentNumber" placeholder="Apartment number">
                        </label>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="2"><input class="floating-button" type="submit" value="Зберегти"></td>
                </tr>
            </table>
        </form>
    </div>
    <table style="height: 30vh">
        <tr>
            <td>
            </td>
        </tr>
    </table>
</body>
</html>