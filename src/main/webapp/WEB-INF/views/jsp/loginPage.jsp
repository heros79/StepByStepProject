<%--
&lt;%&ndash;
  Created by IntelliJ IDEA.
  User: David
  Date: 5/9/2018
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
&ndash;%&gt;
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../resources/css/index.css">
    <title>LavShuka</title>
</head>
<body>

&lt;%&ndash;Login and Registration form&ndash;%&gt;
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







</body>
</html>
--%>
