<%--
&lt;%&ndash;
  Created by IntelliJ IDEA.
  User: David
  Date: 5/11/2018
  Time: 2:39 AM
  To change this template use File | Settings | File Templates.
&ndash;%&gt;
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/register" method="post" name="registration">
    Login <input type="text" name="login"> </br>
    First Name <input type="text" name="firstName"/> </br>
    Last Name <input type="text" name="lastName"/> </br>
    E-mail <input type="email" name="email"> </br>
    Password <input type="password" name="password"> </br>
    Confirm Password <input type="password" name="confirmPassword"> </br>
    <input type="submit" value="Register"> </br>
    ${massage}
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>
--%>
