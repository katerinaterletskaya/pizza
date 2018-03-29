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
    <img src="resources/images/glavnaya.jpg" width="780px" height="110.76px" class="im1">
    <p class="user">${sessionScope.user} (${sessionScope.role})</p>
    <button type="button" class="btn btn-link" onclick="location.href='${pageContext.request.contextPath}/logOut'">Log
        out
    </button>
</header>
<nav class="navbar navbar-light bg-faded" style="background-color: #f9e0a9;">
    <a class="navbar-brand" href="#">Orders</a>
</nav>
<table class="table table-striped">
    <thead>
    <tr>
        <th>№</th>
        <th>User</th>
        <th>Names</th>
        <th>Price</th>
        <th>Price of the order</th>
        <th>Status</th>
        <th>Made order</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listAllOrder}" var="order" varStatus="i">
        <tr>
            <th scope="row">${i.count}</th>
            <td>${order.user}</td>
            <td><c:forEach items="${order.pizzas}" var="pizza">
                ${pizza.namePizza}<br/> <!-- -->
            </c:forEach></td>
            <td><c:forEach items="${order.pizzas}" var="pizza">
                ${pizza.price} BYN<br/>
            </c:forEach></td>
            <td>${order.price} BYN</td>
            <td>${order.status}</td>
            <form action="${pageContext.request.contextPath}/admin/change" method="post" class="frm">
                <input type="hidden" value="${order.id}" name="change">
                <td>
                    <c:choose>
                        <c:when test="${order.status==sessionScope.orderStatus}">
                            <button type="submit" class="btn btn-warning">Complete</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn btn-warning" disabled>Complete</button>
                        </c:otherwise>
                    </c:choose>
                </td>
            </form>
            <form action="${pageContext.request.contextPath}/admin/delete" method="post" class="frm">
                <td>
                    <input type="hidden" value="${order.id}" name="idForDelete">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </td>
            </form>
        </tr>
        <tr></tr>
    </c:forEach>
    </tbody>
</table>
</form>
</body>
</html>