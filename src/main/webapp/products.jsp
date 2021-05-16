<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
  <title>Title</title>
  <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
  <div class="form-style-2-heading">
    Already registered!
  </div>
  <table>
    <tr>
      <th>First Name</th>
    </tr>
    <c:forEach items="${products}" var="product">
      <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.code}</td>
        <td>${product.price}</td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>