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
<form action="${pageContext.request.contextPath}/user/card/add" method="post" class="frm">
    <br/>
    <div class="form-group">
        <label for="num">Number card:</label>
        <input type="text" class="form-control" id="num" name="numberCard" placeholder="Enter number card with spaces:">
    </div>
    <div class="form-group">
        <label for="month">Month:</label>
        <input type="text" class="form-control" id="month" name="month" placeholder="Enter month:">
    </div>
    <div class="form-group">
        <label for="year">Year:</label>
        <input type="text" class="form-control" id="year" name="year" placeholder="Enter year:">
    </div>
    <div class="form-group">
        <label for="description">Description:</label>
        <input type="text" class="form-control" id="description" name="description" placeholder="Enter description:">
    </div>
    <button type="submit" id="popover" class="btn btn-success">Add</button>
</form>
</body>
</html>
