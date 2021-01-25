<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>Save book</title>
</head>
<body>
<jsp:include page="../_header.jsp"/>
<form action="/book/save" method="post">
    <input type="text" name="title">
    <select name="authorId">
        <c:forEach var="author" items="${requestScope.authors}">
            <option value="${author.id}">${author.name}</option>
        </c:forEach>
    </select>
    <select name="categoryId">
        <c:forEach var="category" items="${requestScope.categories}">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select>
    <input type="text" name="description">
    <input type="number" name="price">
    <button>Save</button>
</form>
${requestScope.message}

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</body>
</html>
