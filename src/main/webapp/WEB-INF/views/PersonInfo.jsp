<%--
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
&lt;%&ndash;
  Created by IntelliJ IDEA.
  User: David
  Date: 5/18/2018
  Time: 1:59 AM
  To change this template use File | Settings | File Templates.
&ndash;%&gt;
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<H1> Your data</H1> <br>
<sec:authentication var="principal" property="principal"/>
${principal.username}
First Name ${firstName};
Last Name  ${lastName};
E-mail     ${email};
<form action="/changeaccount" method="post">
    <input type="submit" value="Change Data"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>


</body>
</html>
--%>
