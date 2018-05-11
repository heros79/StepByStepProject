<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 5/11/2018
  Time: 2:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/reg" method="post" name="registration">
    Login <input type="text" name="login"> </br>
    First Name <input type="text" name="firstName"/> </br>
    Last Name <input type="text" name="lastName"/> </br>
    E-mail <input type="email" name="email"> </br>
    Password <input type="password" name="pass"> </br>
    Confirm Password <input type="password" name="confirmPass"> </br>
    <input type="submit" value="Register"> </br>
</form>

</body>
</html>
