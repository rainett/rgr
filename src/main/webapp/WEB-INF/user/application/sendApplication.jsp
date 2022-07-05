<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="main.commands.CommandName" %>
<%@page import="main.Path" %>
<%@page import="main.db.entities.Role" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Надіслати заявку" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>

<%@include file="/WEB-INF/jspf/header.jspf"%>


    <div class="floating-div">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="${CommandName.COMMAND__SEND_APPLICATION}"/>
            <table>
                <c:forEach items="${Role.values()}" var="r">
                    <tr class="floating-row-s">
                        <td>
                            <label>
                                <input type="radio" value="${r.ordinal()}" name="vacancy">${r.roleName}
                            </label>
                        </td>
                    </tr>
                </c:forEach>
                <tr class="floating-row">
                    <td>
                        <input type="submit" class="floating-button" value="Підтвердити">
                    </td>
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