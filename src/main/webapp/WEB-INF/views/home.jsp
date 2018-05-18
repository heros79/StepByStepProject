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
    <script src="../../resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        function openImageWindow(src) {
            var image = new Image();
            image.src = src;
            var width = image.width;
            var height = image.height;
            window.open(src, "Image", "width=" + width + ",height=" + height);
        }
    </script>

    <script type="text/javascript">
        function minus() {
            $('.minus').click(function () {
                var input = this.parent().find('input');
                var count = parseInt(input.val()) - 1;
                count = count < 1 ? 1 : count;
                input.val(count);
                input.changedTouches();
                return false;
            });
        }

        function plus() {
            $('.plus').click(function () {
                var input = this.parent().find('input');
                input.val(parseInt(input.val()) + 1);
                input.changedTouches();
                return false;
            })
        }
    </script>
    <link rel="stylesheet" type="text/css" href="../../resources/css/index.css">
    <style type="text/css">
        span {
            cursor: pointer;
        }

        .number {
            margin: 100px 30%;
        }

        .minus, .plus {
            width: 10px;
            height: 10px;
            background: #f2f2f2;
            border-radius: 4px;
            padding: 3px 5px 3px 5px;
            border: 1px solid #ddd;
        }

        input {
            height: 24px;
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 0 2px;
        }
    </style>
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
        ${principal.username} <br>
        Your balanse ${money} AMD
        <form action="/account/${principal.username}" method="post" name="userInfo">
            <input type="submit" name="goToInfo" value="Your account"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
            </h2>
            <br>
        </c:if>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div id="login" class="header-class">
            <form action="/admin" method="get" name="admin">
                <input type="submit" name="ADMIN" value="Product Service">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <form id="logoutForm" method="POST" action="${contextPath}/logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
                </h2>
                <br>
            </c:if>
        </div>
    </sec:authorize>
</div>

<div class="container" id="choice">
    <form action="/searchByCategory" method="post" name="searchByCategory">
        <div id="choiceProductCategory">
            <select name="categoryChoice" onchange="this.form.submit()">
                <option value="--||--">
                    --||--
                </option>
                <option value="choiceAllCategory">
                    All
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
                <option value="choiceAllType">
                    All
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
                <option value="choiceAllBrand">
                    All
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

<div class="container" style="visibility: hidden">
    <div class="catalog" style="visibility: hidden">
        <c:forEach items="${products}" var="item">
            <div>
                <div class="item" style="background: url('${item.productImageFilePath}'); background-size: auto 100%;
                        background-repeat: no-repeat; visibility: visible;"
                     onclick="openImageWindow('${item.productImageFilePath}')">
                    <p>${item.productName}</p>
                    <p>${item.price}AMD</p>
                </div>
                <div class="number"
                     style="position: inherit; vertical-align: top; text-align: center; visibility: visible">
                    <span class="minus" onclick="minus()">-</span>
                    <input type="text" value="1" size="5" name="SelectProductCount" onchange="alert(this.value)"/>
                    <span class="plus" onclick="plus()">+</span>
                </div>
                <form action="/addProductToCorf">
                    <input type="submit" value="BUY">
                </form>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
