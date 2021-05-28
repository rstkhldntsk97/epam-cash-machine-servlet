<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="z.report.title"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</head>
<body>
<%@ include file="menu.jspf" %><br/><br/>
<div class="alert alert-warning" role="alert">
    <h4 class="alert-heading">Z-REPORT</h4>
    <p><fmt:message key="count.of.closed.checks.message"/> <strong>${requestScope.count}</strong></p>
    <p><fmt:message key="money.received.message"/> <strong>${requestScope.TOTAL_SUM}</strong></p>
</div>

<form action="${pageContext.request.contextPath}/logout" class="form-inline my-2 my-lg-0" method="post">
    <button class="btn btn-outline-warning my-2 my-sm-0" type="submit"><fmt:message key="menu.button.logout"/></button>
</form>

</body>
</html>
