<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>Filter</title>
</head>
<body>
<jsp:include page="../_header.jsp"/>

<div class="container">
    <c:forEach items="${requestScope.books}" var="book">
        <div class="row justify-content-center m-3">
            <div class="card col-8">
                <div class="card-body">
                    <h5 class="card-title">${book.title}</h5>
                    <p class="card-text">${book.description}</p>
                    <a href="/book/viewBook?id=${book.id}" class="btn btn-primary">Open</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
