<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/17/2018
  Time: 1:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/remove" method="post">
    User Login <input type="text" name="userLogin"><br>
    <input type="submit" value="Remove User">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    ${massage}
</form>
<br>

<form action="/addCategory" method="post">
    Input new Category <input type="text" name="addCategory"><br>
    <input type="submit" value="add Category">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    ${categoryMassage}
</form>
<br>


<form action="/addType" method="post">
    Select Gategory <select name="categoryChoice">
    <option value="--||--">
        --||--
    </option>
    <c:forEach items="${categoryList}" var="item">
        <option value="${item.productCategoryName}">
                ${item.productCategoryName}
        </option>
    </c:forEach>
</select>
    Input new Type <input type="text" name="addType"><br>
    <input type="submit" value="add Type">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    ${typeMassage}
</form>
<br>

<form action="/addBrand" method="post">
    Input new Brand <input type="text" name="addBrand"><br>
    <input type="submit" value="add Brand">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<br>

<form action="/addNewProduct" method="post">

    Select Category <select name="categoryChoice">
    <option value="--||--">
        --||--
    </option>
    <c:forEach items="${categoryListForProduct}" var="item">
        <option value="${item.productCategoryName}">
                ${item.productCategoryName}
        </option>
    </c:forEach>
</select><br>

    Select Type <select name="typeChoice">
    <option value="--||--">
        --||--
    </option>
    <c:forEach items="${typeList}" var="item">
        <option value="${item.productTypeName}">
                ${item.productTypeName}
        </option>
    </c:forEach>
</select><br>

    Select Brand <select name="brandChoice">
    <option value="--||--">
        --||--
    </option>
    <c:forEach items="${brandList}" var="item">
        <option value="${item.productBrandName}">
                ${item.productBrandName}
        </option>
    </c:forEach>
</select><br>

    Product Name <input type="text" name="productName"> Price <input type="number" name="productPrice" min="0"
                                                                     step=".01"><br>
    Description <input type="text" name="description" size="75"><br>
    VendorCode <input type="text" name="vendorCode"> FilePath <input type="text" name="filePath"><br>
    <input type="submit" value="Add Product">

    ${productMassage}
</form>

<form action="/addProductCount" method="post">
    Select Product <select name="productChoice">
    <option value="--||--">
        --||--
    </option>
    <c:forEach items="${productList}" var="item">
        <option value="${item.vendorCode}">
                ${item.productName} &nbsp ${item.vendorCode}
        </option>
    </c:forEach>
    </select><br>
    Select count<input type="number" min="0" name="productCount">
    <input type="submit" value="ADD">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
