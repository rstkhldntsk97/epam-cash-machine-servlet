<%--<%@ page import="java.util.ArrayList" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title><fmt:message key="admin.title"/></title>--%>
<%--</head>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="admin.title"/></title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="menu.jspf" %><br/>
<table>
    <tr>
        <th>User name</th>
        <th>Password</th>
        <th>Role</th>
    </tr>
    <c:forEach items="${usersFromServer}" var="user">
        <tr>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.role}</td>
        </tr>
    </c:forEach>
</table>
<form class="form-signin" action="${pageContext.request.contextPath}/adminPage" method="post">

    <input type="username"  name="username" class="form-control" placeholder="Username" required>
    <input type="password" name="password" class="form-control" placeholder="Password" required>
    <select name="role" id="role" multiple>
        <option value="CASHIER">Cashier</option>
        <option value="SENIOR_CASHIER">Senior cashier</option>
        <option value="COMMODITY_EXPERT">Commodity expert</option>
    </select>
    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"><fmt:message
            key="admin.create.new.user"/></button>

</form>
</body>
</html>
