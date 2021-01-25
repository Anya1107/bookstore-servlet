<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>Save order</title>
</head>
<body>
<jsp:include page="../_header.jsp"/>
<form action="/order/save" method="post">
    <select name="storeId">
        <c:forEach var="store" items="${requestScope.stores}">
            <option value="${store.id}">${store.name}</option>
        </c:forEach>
    </select>
    <select name="userId">
        <c:forEach var="user" items="${requestScope.users}">
            <option value="${user.id}">${user.firstName}</option>
        </c:forEach>
    </select>
    <select name="isDelivery">
        <option value="true">Delivery</option>
        <option value="false">Pickup</option>
    </select>
    <select name="addressId">
        <c:forEach var="address" items="${requestScope.addresses}">
            <option value="${address.id}">${address.address}</option>
        </c:forEach>
    </select>
    <button>Save</button>
</form>
${requestScope.message}

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
