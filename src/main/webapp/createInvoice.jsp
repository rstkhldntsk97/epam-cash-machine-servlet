<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<head>
    <meta charset="UTF-8">
    <title><fmt:message key="cashier.title"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="menu.jspf" %>
<br/>
<br/>
<br/>
<br class="container">
    <c:if test="${message ne null}">
        <div class="alert alert-dismissible alert-${type}">
            <button type="button" class="close" data-dismiss="alert" aria-label="close" aria-hidden="true">&times;</button>
            <span>${message}</span>
        </div>
    </c:if>
    <c:remove var="message" scope="session"/>
    <c:remove var="type" scope="session"/>
    <table>
        <form class="form-signin" action="${pageContext.request.contextPath}/createInvoice" method="post">
            <fmt:message key="check.product.code"/><br/>
            <table>
                <input type="code" name="code" class="form-control"
                       placeholder="<fmt:message key="product.code"/>"><br/>
                <input type="quantity" name="quantity" class="form-control"
                       placeholder="<fmt:message key="product.quantity"/>"><br/>
            </table>
            <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"><fmt:message
                    key="button.add.product.to.invoice"/></button>
        </form>
        <br/>
        <form class="form-signin" action="${pageContext.request.contextPath}/updateInvoice" method="post">
            <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"><%session.setAttribute("status","CLOSED");%><fmt:message
                    key="button.close.invoice"/></button>
        </form>
    </table>
</div>
</body>
