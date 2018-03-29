<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>PiZZa</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainPizza.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/validate.js"></script>
</head>
<body>
<header class="head">
    <img src="${pageContext.request.contextPath}/resources/images/glavnaya.jpg" width="780px" height="110.76px"
         class="im1">
    <p class="user">${sessionScope.user} (${sessionScope.role})</p>
    <button type="button" class="btn btn-link" onclick="location.href='${pageContext.request.contextPath}/logOut'">Log out</button>
</header>
<nav class="navbar navbar-light bg-faded" style="background-color: #f9e0a9;">
    <ul class="nav navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/user">Catalog<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/user/cart">Cart ${sessionScope.number}</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/user/order">Orders</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/user/card">Cards</a>
        </li>
    </ul>
</nav>
<c:choose>
    <c:when test="${sizeListCart!=0}">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>№</th>
                <th>Name</th>
                <th>Price</th>
                <th>Add to order</th>
            </tr>
            </thead>
            <tbody>
            <form action="${pageContext.request.contextPath}/user/order/add" method="post" name="cart"
                  onsubmit="return validate();">
                <c:forEach items="${listCart}" var="pizza" varStatus="i">
                <tr>
                    <td>${i.count}</td>
                    <td>${pizza.namePizza}</td>
                    <td>${pizza.price} BYN</td>
                    <td>
                        <input type="checkbox" value="${pizza.idPizza}" name="idPizza">
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <input type="hidden" value="${sessionScope.user}" name="userName">
        <button type="submit" class="btn btn-success col-md-offset-10">To order</button>
        </form>
    </c:when>
    <c:otherwise>
        <h3 class="display-3" style="text-align: center">Cart is empty.</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
