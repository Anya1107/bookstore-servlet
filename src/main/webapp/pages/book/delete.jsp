<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>Delete book</title>
</head>
<body>
<jsp:include page="../_header.jsp"/>
<form action="/book/delete" method="post">
    <select name="bookId">
        <c:forEach var="book" items="${requestScope.books}">
            <option value="${book.id}">${book.title}</option>
        </c:forEach>
    </select>
    <button>Delete</button>
</form>
${requestScope.message}
</body>
</html>
