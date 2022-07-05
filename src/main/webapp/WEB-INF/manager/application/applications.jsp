<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="main.commands.CommandName" %>
<%@page import="main.Path" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="Заявки" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>

<%@include file="/WEB-INF/jspf/header.jspf"%>

    <div class="floating-div">
        <table>
            <c:if test="${empty requestScope.applications}">
                <tr class="floating-row-l">
                    <td>Заявок нема</td>
                </tr>
            </c:if>
            <c:if test="${not empty requestScope.applications}">
            <tr class="floating-row">
                <td>Номер заявки</td>
                <td>Бажана посада</td>
                <td>Стан</td>
            </tr>
            </c:if>
            <c:forEach items="${requestScope.applications}" var="a">
                <tr class="floating-row-s-clickable" onclick="document.forms['updateForm${a.id}'].submit();">
                    <td>
                        <form id="updateForm${a.id}" action="controller">
                            ${a.id}
                            <input type="hidden" name="command" value="${CommandName.COMMAND__SHOW_APPLICATION_PROCESS}">
                            <input type="hidden" name="applicationId" value="${a.id}">
                        </form>
                    </td>
                    <td>${a.roleId}</td>
                    <td>${a.state == true ? "Розглянута" : "Нерозглянута"}</td>
                </tr>
            </c:forEach>
            <tr class="floating-row-s"></tr>
        </table>
    </div>
    <table style="height: 30vh">
        <tr>
            <td>
            </td>
        </tr>
    </table>
</body>
</html>