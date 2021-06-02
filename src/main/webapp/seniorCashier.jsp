<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="senior.cashier.title"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>

<%@ include file="menu.jspf" %><br/>


<div class="container">

    <form action="${pageContext.request.contextPath}/editInvoice" method="get">
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block"><fmt:message
                    key="button.edit.invoice"/></button>
        </div>
    </form>

    <form action="${pageContext.request.contextPath}/reportX" method="get">

        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block"><fmt:message
                    key="button.create.report.X"/></button>
        </div>
    </form>

    <form action="${pageContext.request.contextPath}/reportZ" method="get">

        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block"><fmt:message
                    key="button.create.report.Z"/></button>
        </div>
    </form>

</div>


</body>
</html>