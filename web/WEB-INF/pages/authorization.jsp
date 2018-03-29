<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Authorization</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/authorization.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</head>
<body>
<header class="head">
    <img src="${pageContext.request.contextPath}/resources/images/glavnaya.jpg" width="500px" height="71px">
</header>
<img src="resources/images/images.png" class="im" align="right">
<form action="${pageContext.request.contextPath}/" method="post" class="frm">
    <br/>
    <div class="form-group">
        <label for="usr">Name:</label>
        <input type="text" class="form-control" id="usr" name="username" placeholder="Enter login">
    </div>
    <div class="form-group">
        <label for="psw">Password:</label>
        <input type="password" class="form-control" id="psw" name="password" placeholder="Enter password">
    </div>
    <button type="submit" id="popover" class="btn btn-default">Log in</button>
</form>
<c:if test="${sessionScope.info!=null}">
    <div style="color: #ff0000">
        <p style="margin-left: 5%">Errors:</p>
        <c:forEach var="error" items="${sessionScope.info}">
            <p style="margin-left: 5%">● ${error}</p>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
