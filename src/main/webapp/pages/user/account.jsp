<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>Account</title>
</head>
<body>
<jsp:include page="../_header.jsp"/>
User: ${sessionScope.user}

<ul>
    <li>
    <a href="/user/updateFirstName">Update first name</a>
    </li>
    <li>
    <a href="/user/updateLastName">Update last name</a>
    </li>
    <li>
    <a href="/user/updatePassword">Update password</a>
    </li>
</ul>

<ul>
    <c:forEach items="${sessionScope.basket.books}" var="book">
        <li>${book.title}</li>
    </c:forEach>
</ul>
<a href="/order/createOrder">Create order</a>

<ul>
    <c:forEach items="${requestScope.orders}" var="order">
        <a href="/order/viewOrder?id=${order.id}">
            <li>${order.address} ${order.store} ${order.date}</li>
        </a>
    </c:forEach>
</ul>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
