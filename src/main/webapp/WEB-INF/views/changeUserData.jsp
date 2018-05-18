<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/18/2018
  Time: 2:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<sec:authentication var="principal" property="principal"/>
<form action="/account/${principal.username}/changedata" method="post">
    ${principal.username}
    Old Password <input type="password" name="oldPassword"/><br>
    New Email <input type="text" name="newEmail"/><br>
    New Password <input type="password" name="newPassword"/><BR>
    Confirm Password <input type="password" name = "confirmNewPassword"/><br>
    <input type="submit" value="Change Data"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>
