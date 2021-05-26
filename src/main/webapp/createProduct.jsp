<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="create.product.title"/></title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body class="hm-gradient">

<%@ include file="menu.jspf" %>
<br/>

<div class="container">

    <form action="${pageContext.request.contextPath}/createProduct.jsp" method="post">
        <div class="form-group input-group">
            <div class="input-group-prepend">
                <span class="input-group-text"> <i class="fa fa-user"></i> </span>
            </div>
            <input name="name of product" class="form-control" placeholder="Product Name" type="text" required>
        </div>

        <div class="form-group input-group">
            <div class="input-group-prepend">
                <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
            </div>
            <input name="password" class="form-control" placeholder="Product Code" type="password" required>
        </div>

        <div class="form-group input-group">
            <div class="input-group-prepend">
                <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
            </div>
            <input name="password" class="form-control" placeholder="Price" type="password" required>
        </div>

        <div class="form-group input-group">
            <div class="input-group-prepend">
                <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
            </div>
            <input name="password" class="form-control" placeholder="Quantity" type="password" required>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block"><fmt:message
                    key="button.submit"/></button>
        </div>
    </form>

</div>

</body>

</html>
