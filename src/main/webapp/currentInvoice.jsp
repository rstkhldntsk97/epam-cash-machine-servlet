<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<head>
    <meta charset="UTF-8">
    <title><fmt:message key="senior.cashier.title"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="menu.jspf" %>
<br/>
<div class="card bg-light">
    <article class="card-body mx-auto" style="max-width: 400px;">
        <h4 class="card-title mt-3 text-center"><fmt:message key="invoice.no"/> ${invoice.id}</h4>
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <th><fmt:message key="cashier.title"/></th>
            </tr>
            <td>${invoice.user.username}</td>
            <tr>
                <th><fmt:message key="invoice.date"/></th>
            </tr>
            <td>${invoice.createdAt}</td>
            <tr>
                <th><fmt:message key="form.invoice.status"/></th>
            </tr>
            <td>${invoice.status}</td>
            <tr>
                <th><fmt:message key="invoice.total.price"/></th>
            </tr>
            <td>${invoice.total}</td>
            <tr>
                <th><fmt:message key="form.invoice.all.products"/></th>
            </tr>
            <td>
                <c:forEach items="${invoice.products}" var="product">
                    <c:out value="${product.key}"/><br/>
                </c:forEach>
            </td>
        </table>

    </article>
    <form class="form-signin" action="${pageContext.request.contextPath}/updateInvoice" method="post">
        <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"> <%session.setAttribute("status","DECLINED");%>
            <fmt:message key="button.decline.invoice"/></button>
    </form>

    <form class="form-signin" action="${pageContext.request.contextPath}/deleteProduct" method="post">

    <input type="username" name="product" class="form-control" placeholder="<fmt:message key="product.name.EN"/>" required>
    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"><fmt:message
            key="button.delete"/></button>

</form>
</div>
</body>