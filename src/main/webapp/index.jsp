<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE HTML>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><fmt:message key="index.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>

<%@ include file="menu.jspf" %>

<main role="main">
    <div class="jumbotron">
        <div class="container" style="text-align: center">
            <h1><fmt:message key="index.presentation"/></h1>
            <p><fmt:message key="info.index.service.description"/></p>
        </div>

    </div>
</main>

<footer role="contentinfo" class="footer">
    <div class="container" style="text-align: end">
        <span class="text-muted">© Rostyslav Kholodnytskyi, 2021</span>
    </div>
</footer>

</body>

</html>