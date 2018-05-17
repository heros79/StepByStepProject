<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/9/2018
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../resources/css/index.css">
    <title>LavShuka</title>
</head>
<body>

<div id="header">
    <sec:authorize access="isAnonymous()">
        <div id="login" class="header-class">
            <form action="/loginPage" method="get" name="logining">
                <input type="submit" name="USER" value="LOGIN">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_USER')">
        <sec:authentication var="principal" property="principal"/>
        ${principal.username}
        ${principal.authorities}
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div id="login" class="header-class">
            <form action="/admin" method="get" name="admin">
                <input type="submit" name="ADMIN" value="Product Service">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </sec:authorize>

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


<div>
    <form action="/searchByCategory" method="post" name="searchByCategory">
        <div id="choiceProductCategory">
            <select name="categoryChoice" onchange="this.form.submit()">
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
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <form action="/searchByType" method="post" name="searchByType">
        <div id="choiceProductType">
            <select name="typeChoice" onchange="this.form.submit()">
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
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <form action="/searchByBrand" method="post" name="searchByBrand">
        <div id="choiceProductBrand">
            <select name="brandChoice" onchange="this.form.submit()">
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
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>

</body>
</html>
