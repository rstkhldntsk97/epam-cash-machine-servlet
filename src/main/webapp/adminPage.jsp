<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="admin.title"/></title>
</head>

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
    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">CREATE NEW USER</button>
    <a class="nav-link" href="${pageContext.request.contextPath}/logout">LOGOUT</a>

</form>
</html>
