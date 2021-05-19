<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
  <title>Title</title>
  <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
  <div class="form-style-2-heading">
    Our products
  </div>
  <table>
    <c:forEach items="${products}" var="product">
      <tr>
        <td>${product.name}</td>
        <td>${product.code}</td>
        <td>${product.price}</td>
        <td>${product.quantity}</td>
      </tr>
    </c:forEach>
  </table>
  <form class="form-signin" action="${pageContext.request.contextPath}/createProduct" method="get">
    <button type="submit" class="btn btn-primary btn-block"><fmt:message key="expert.button.create.product"/></button>
    <p class="text-center"><a href="${pageContext.request.contextPath}/createProduct"></a></p>
  </form>
</div>
</body>
</html>