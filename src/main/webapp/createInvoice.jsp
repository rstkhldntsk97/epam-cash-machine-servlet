<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Invoice</title>
</head>
<body>
<table>
    </tr>name="<%=request.getAttribute("productByCodeFromServer")%>"</tr>

</table>
<form class="form-signin" action="${pageContext.request.contextPath}/createInvoice" method="post">

    <input type="code" name="code" class="form-control" placeholder="product code">
    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit"><fmt:message
            key="cashier.button.search.product"/></button>
</form>
</body>
</html>
