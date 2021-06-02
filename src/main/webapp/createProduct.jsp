<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="expert.title"/></title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body class="hm-gradient">

<%@ include file="menu.jspf" %>
<br/>

<div class="container">
    <c:if test="${message ne null}">
        <div class="alert alert-dismissible alert-${type}">
            <button type="button" class="close" data-dismiss="alert" aria-label="close" aria-hidden="true">&times;
            </button>
            <span>${message}</span>
        </div>
    </c:if>
    <c:remove var="message" scope="session"/>
    <c:remove var="type" scope="session"/>
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center"><fmt:message key="expert.button.create.product"/></h4>
            <form class="form-signin" action="${pageContext.request.contextPath}/commodityExpert" method="post">

                <input type="name" name="nameEN" class="form-control" placeholder="<fmt:message key="product.name.EN"/>"
                       required>
                <input type="name" name="nameUA" class="form-control" placeholder="<fmt:message key="product.name.UA"/>"
                       required>
                <input type="price" name="price" class="form-control"
                       placeholder="<fmt:message key="product.price"/>" required>
                <input type="quantity" name="quantity" class="form-control"
                       placeholder="<fmt:message key="product.quantity"/>" required>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"><fmt:message
                        key="button.submit"/></button>
            </form>
        </article>
    </div>

</div>

</body>

</html>
