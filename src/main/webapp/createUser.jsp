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

    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center"><fmt:message key="admin.create.new.user"/></h4>
            <form class="form-signin" action="${pageContext.request.contextPath}/addUser" method="post">

                <input type="username"  name="username" class="form-control" placeholder="Username" required>
                <input type="password" name="password" class="form-control" placeholder="Password" required>
                <select name="role" id="role" multiple>
                    <option value="CASHIER">Cashier</option>
                    <option value="SENIOR_CASHIER">Senior cashier</option>
                    <option value="COMMODITY_EXPERT">Commodity expert</option>
                </select>

                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"><fmt:message
                        key="button.submit"/></button>

            </form>
        </article>
    </div>

</div>

</body>

</html>
