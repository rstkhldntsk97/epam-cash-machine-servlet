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
<%@ include file="menu.jspf" %><br/>


<table>
    </tr>name="<%=request.getAttribute("productByCodeFromServer")%>"</tr>


    <form class="form-signin" action="${pageContext.request.contextPath}/createInvoice" method="post">

        <input type="code" name="code" class="form-control" placeholder="product code">
        <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"><fmt:message
                key="cashier.button.add.product.to.invoice"/></button>
    </form>
    <form class="form-signin" action="${pageContext.request.contextPath}/closeInvoice" method="get">
        <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"><fmt:message
            key="cashier.button.close.invoice"/></button>
    </form>
</table>
</body>
