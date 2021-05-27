<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="admin.title"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="menu.jspf" %>
<br/>
<div class="card bg-light">
    <article class="card-body mx-auto" style="max-width: 400px;">
        <h4 class="card-title mt-3 text-center"><fmt:message key="admin.list.users"/></h4>
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
    </article>
</div>
</body>
</html>
