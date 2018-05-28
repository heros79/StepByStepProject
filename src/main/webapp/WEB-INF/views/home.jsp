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

        function buy(cidr) {
            var count = document.getElementById(cidr).value;
            var corf = document.getElementById('corfCount');
            corf.value = count;
        }

        function checkSum(price) {
            var count = parseInt(document.getElementById('corfCount').value);
            var money = '${money}';
            var sum = count * price;
            if (sum > money) {
                document.getElementById('corfCount').value = 0;
                alert('You not have money');
            }
        }

    </script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('.minus').click(function () {
                var $input = $(this).parent().find('input');
                var count = parseInt($input.val()) - 1;
                count = count < 1 ? 1 : count;
                $input.val(count);
                $input.change();
                return false;
            });
            $('.plus').click(function () {
                var $input = $(this).parent().find('input');
                $input.val(parseInt($input.val()) + 1);
                $input.change();
                return false;
            });
        });
    </script>
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
        ${principal.username} <br>
        Your balanse ${money} AMD || YourCorf <input type="number" id="corfCount" size="5" value="0" readonly/>
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
                <h2>Welcome ${pageContext.request.userPrincipal.name} | <a
                        onclick="document.forms['logoutForm'].submit()">Logout</a>
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
            <div class="item">
                <div id="f_product" style="background: url('${item.productImageFilePath}'); background-size: auto 100%;
                        background-repeat: no-repeat; visibility: visible;"
                     onclick="openImageWindow('${item.productImageFilePath}')">
                </div>
                <div id="n_product" style="visibility: visible">
                    <p>${item.productName}</p>
                </div>
                <div id="p_product" style="visibility: visible;">
                    <p>${item.price}AMD</p>
                </div>
                <div class="number"
                     style="position: inherit; vertical-align: top; text-align: center; visibility: visible">
                    <span class="minus">-</span>
                    <input type="text" value="1" min="1" max="" size="5" name="SelectProductCount"
                           id="${item.vendorCode}" class="count"/>
                    <span class="plus">+</span>
                </div>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <input type="button" id="buyCount" value="BUY"
                           style="visibility: visible; margin: 39px; margin-top: auto" onclick="buy('${item.vendorCode}'), checkSum('${item.price}') ">
                </sec:authorize>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
--%>
