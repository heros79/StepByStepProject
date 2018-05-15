<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/9/2018
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../resources/css/index.css">
    <title>LavShuka</title>
</head>
<body>

<%--Login and Registration form--%>
<div id="header">
    <div id="loginPage" class="header-class">
        <form action="/login" method="post" name="logining" id="login-form">
            Login <input type="text" name="username" size="10"/>
            Password <input type="password" name="password" size="10">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="LOGIN"/> </br>
        </form>
        <div id="registration" class="header-class">
            <a href="${contextPath}/register"> REGISTRATION </a>
        </div>
    </div>
</div>


<div>
<%--    <form action="/search" method="get" name="search">--%>
        <div id="choseProductCategory">
            <select name="category" onchange="this.form.submit()">
                <option value="--||--">
                    --||--
                </option>
                <c:forEach items="${categoryList}" var="item">
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
                <c:forEach items="${typeList}" var="item">
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
                <c:forEach items="${brandList}" var="item">
                    <option value="${item.productBrandName}">
                            ${item.productBrandName}
                    </option>
                </c:forEach>
            </select>
        </div>
        <input type="submit" value="SEARCH">
    <%--</form>--%>
</div>

<div id="prodview">
    <c:forEach items="${products}" var="item">
        <div style="width: 600px; margin-top: 50px;" class="center">
            <img src="${item.productImageFilePath}" style="width: 100px; height: 100px; float: left;"/>
            <h4 style="float: left;">${item.productName}</h4>
            <p style="float: right;">${item.price}AMD</p>
            <div style="clear: both; "></div>
        </div>
        <div style="clear: both; "></div>
    </c:forEach>
</div>


</body>
</html>
