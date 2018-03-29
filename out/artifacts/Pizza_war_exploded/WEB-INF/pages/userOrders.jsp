<%@ page contentType="text/html;charset=UTF-8" language="java"
         isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
    <title>PiZZa</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainPizza.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</head>
<body>
<header class="head">
    <img src="${pageContext.request.contextPath}/resources/images/glavnaya.jpg" width="780px" height="110.76px" class="im1">
    <p class="user">${sessionScope.user} (${sessionScope.role})</p>
    <button type="button" class="btn btn-link" onclick="location.href='${pageContext.request.contextPath}/logOut'">Log out</button>
</header>
<nav class="navbar navbar-light  bg-faded" style="background-color: #f9e0a9;">
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
    <c:when test="${sizeListOrders!=0}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Names</th>
            <th>Price</th>
            <th>Price of the order</th>
            <th>Status</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listOrders}" var="order" varStatus="i">
            <tr>
                <th scope="row">${i.count}</th>
                <td><c:forEach items="${order.pizzas}" var="pizza">
                    ${pizza.namePizza}<br/>
                </c:forEach></td>
                <td><c:forEach items="${order.pizzas}" var="pizza">
                    ${pizza.price} BYN<br/>
                </c:forEach></td>
                <td>${order.price} BYN</td>
                <td>${order.status}</td>
                <form action="${pageContext.request.contextPath}/user/order/delete" method="post" class="frm">
                    <input type="hidden" value="${order.id}" name="idForDelete">
                    <td>
                        <c:choose>
                            <c:when test="${order.status=='Waiting'}">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit" class="btn btn-danger" disabled>Delete</button>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </form>
            </tr>
            <tr></tr>
        </c:forEach>
        </tbody>
    </table>
</c:when>
<c:otherwise>
    <h3 class="display-3" style="text-align: center">Order's list is empty.</h3>
</c:otherwise>
</c:choose>
</body>
</html>
