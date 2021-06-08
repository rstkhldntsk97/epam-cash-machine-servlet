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
    <br/><br/>
    <c:if test="${message ne null}">
        <div class="alert alert-dismissible alert-${type}">
            <button type="button" class="close" data-dismiss="alert" aria-label="close" aria-hidden="true">&times;
            </button>
            <span>${message}</span>
        </div>
    </c:if>
    <c:remove var="message" scope="session"/>
    <c:remove var="type" scope="session"/>
    <article class="card-body mx-auto" style="max-width: 400px;">
        <h4 class="card-title mt-3 text-center"><fmt:message key="senior.list.invoices"/></h4>
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <th><fmt:message key="form.invoice.id"/></th>
                <th><fmt:message key="cashier.title"/></th>
                <th><fmt:message key="invoice.total.price"/></th>
                <th><fmt:message key="invoice.date"/></th>
                <th><fmt:message key="form.invoice.status"/></th>
            </tr>
            <c:forEach items="${invoicesFromServer}" var="invoice">
                <tr>
                    <td>${invoice.id}</td>
                    <td>${invoice.user.username}</td>
                    <td>${invoice.total}</td>
                    <td>${invoice.createdAt}</td>
                    <td>${invoice.status}</td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <table>
            <form class="form-signin" action="${pageContext.request.contextPath}/editInvoice" method="post">
                <fmt:message key="form.edit.invoice.message"/><br/>
                <table>
                    <input type="id" name="id" class="form-control" placeholder="<fmt:message key="form.invoice.id"/>"><br/>
                </table>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"><fmt:message key="form.invoice.edit"/></button>
            </form>
        </table>
    </article>
</div>
</body>