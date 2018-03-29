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
</head>
<body>
<header class="head">
    <img src="${pageContext.request.contextPath}/resources/images/glavnaya.jpg" width="780px" height="110.76px"
         class="im1">
    <p class="user">${sessionScope.user} (${sessionScope.role})</p>
    <button type="button" class="btn btn-link" onclick="location.href='${pageContext.request.contextPath}/logOut'">Log
        out
    </button>
</header>
<nav class="navbar navbar-light  bg-faded" style="background-color: #f9e0a9;">
    <ul class="nav navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/user">Catalog <span
                    class="sr-only">(current)</span></a>
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
<form action="${pageContext.request.contextPath}/user" method="post" class="frm">
    <c:forEach items="${listPizzas}" var="pizza">
        <div class="row">
            <div>
                <div class="col-lg-6">
                    <img src="${pageContext.request.contextPath}/resources/images/pizza/${pizza.imageName}.png"
                         alt=${pizza.namePizza} class="im"/>
                    <div class="caption">
                        <span class="title">${pizza.namePizza}</span>
                        <span class="info">Size: ${pizza.size}cm.<br/>
                        Weight: ${pizza.weight}g<br/>Price: ${pizza.price} BYN</span>
                    </div>
                </div>
                <br/>
                <div class="test">
                    <button type="submit" class="btn btn-default" name="pizzaImageName" value="${pizza.imageName}">Add to cart</button>
                </div>
            </div>
        </div>
    </c:forEach>
</form>
</body>
</html>
