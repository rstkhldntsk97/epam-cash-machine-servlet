<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="x.report.title"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="menu.jspf" %><br/><br/>
<div class="alert alert-info" role="alert">
  <h4 class="alert-heading">X REPORT</h4>
  <p><fmt:message key="count.of.closed.checks.message"/> <strong>${requestScope.COUNT}</strong></p>
  <p><fmt:message key="money.received.message"/> <strong>${requestScope.TOTAL_SUM}</strong></p>
</div>
</body>
</html>
