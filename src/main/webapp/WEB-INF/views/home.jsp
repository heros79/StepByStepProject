<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/9/2018
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>HOME</title>
</head>
<body>
<form action="/search" method="get" name="search">
    <div id="choseProductCategory">
        <select name="category">
            <option value="--||--">
                --||--
            </option>
            <c:forEach items="${category}" var="item">
                <option value="${item.productCategoryName}">
                        ${item.productCategoryName}
                </option>
            </c:forEach>
        </select>
    </div>
    <div id="choseProductType">
        <select name="type">
            <option value="--||--">
                --||--
            </option>
            <c:forEach items="${type}" var="item">
                <option value="${item.productTypeName}">
                        ${item.productTypeName}
                </option>
            </c:forEach>
        </select>
    </div>
    <div id="choseProductBrand">
        <select name="brand">
            <option value="--||--">
                --||--
            </option>
            <c:forEach items="${brand}" var="item">
                <option value="${item.productBrandName}">
                        ${item.productBrandName}
                </option>
            </c:forEach>
        </select>
    </div>
    <input type="submit" value="SEARCH">
</form>

</body>
</html>
