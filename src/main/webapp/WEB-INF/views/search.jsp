<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/11/2018
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>LavShuka</title>
    <style type="text/css">
        .center {
            margin: auto;
            width: 50%;
            border: 3px solid green;
            padding: 10px;
        }
    </style>
</head>
<body>
<div id="prodview">
    <c:forEach items="${products}" var="item">
        <div style="width: 600px; margin-top: 50px;" class="center">
            <img src="${item.productImageFilePath}" style="width: 100px; height: 100px; float: left;" />
            <h4 style="float: left;">${item.productName}</h4>
            <p style="float: right;">${item.price}AMD</p>
            <div style="clear: both; "></div>
        </div>
        <div style="clear: both; "></div>
    </c:forEach>
</div>
</body>
</html>
