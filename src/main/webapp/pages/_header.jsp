<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Book Store</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <c:if test="${sessionScope.isGuest}">
                <li class="nav-item">
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Hello Guest</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/registration">Registration</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/authorization">Authorization</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.isUser}">
                <li class="nav-item">
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">User: Hello ${sessionScope.user.firstName}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/account">Account</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.isAdmin}">
                <li class="nav-item">
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Admin: Hello ${sessionScope.user.firstName}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user/account">Account</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Author menu
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/author/save">Save</a>
                        <a class="dropdown-item" href="/author/delete">Delete</a>
                        <a class="dropdown-item" href="/author/findAll">Find all</a>
                        <a class="dropdown-item" href="/author/findByName">Find by name</a>
                        <a class="dropdown-item" href="/author/findById">Find by id</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown9" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Category menu
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown9">
                        <a class="dropdown-item" href="/category/save">Save</a>
                        <a class="dropdown-item" href="/category/delete">Delete</a>
                        <a class="dropdown-item" href="/category/findById">Find by id</a>
                        <a class="dropdown-item" href="/category/findAll">Find all</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Book menu
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown2">
                        <a class="dropdown-item" href="/book/save">Save</a>
                        <a class="dropdown-item" href="/book/delete">Delete</a>
                        <a class="dropdown-item" href="/book/findAll">Find all</a>
                        <a class="dropdown-item" href="/book/findByTitle">Find by title</a>
                        <a class="dropdown-item" href="/book/findById">Find by id</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Address menu
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown3">
                        <a class="dropdown-item" href="/address/save">Save</a>
                        <a class="dropdown-item" href="/address/delete">Delete</a>
                        <a class="dropdown-item" href="/address/findAll">Find all</a>
                        <a class="dropdown-item" href="/address/findByName">Find by name</a>
                        <a class="dropdown-item" href="/address/findById">Find by id</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown7" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        City menu
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown7">
                        <a class="dropdown-item" href="/city/save">Save</a>
                        <a class="dropdown-item" href="/city/delete">Delete</a>
                        <a class="dropdown-item" href="/city/findAll">Find all</a>
                        <a class="dropdown-item" href="/city/findByName">Find by name</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown4" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Store menu
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown4">
                        <a class="dropdown-item" href="/store/save">Save</a>
                        <a class="dropdown-item" href="/store/delete">Delete</a>
                        <a class="dropdown-item" href="/store/findAll">Find all</a>
                        <a class="dropdown-item" href="/store/findById">Find by id</a>
                        <a class="dropdown-item" href="/store/findByName">Find by name</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown5" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        User menu
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown5">
                        <a class="dropdown-item" href="/user/save">Save</a>
                        <a class="dropdown-item" href="/user/delete">Delete</a>
                        <a class="dropdown-item" href="/user/findAll">Find all</a>
                        <a class="dropdown-item" href="/user/findById">Find by id</a>
                        <a class="dropdown-item" href="/user/findByEmail">Find by email</a>
                        <a class="dropdown-item" href="/user/updateFirstName">Update first name</a>
                        <a class="dropdown-item" href="/user/updateLastName">Update last name</a>
                        <a class="dropdown-item" href="/user/updatePassword">Update password</a>
                        <a class="dropdown-item" href="/user/deleteByEmail">Delete by email</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown6" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Order menu
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown6">
                        <a class="dropdown-item" href="/order/save">Save</a>
                        <a class="dropdown-item" href="/order/delete">Delete</a>
                        <a class="dropdown-item" href="/order/findById">Find by id</a>
                        <a class="dropdown-item" href="/order/findByStore">Find by store</a>
                        <a class="dropdown-item" href="/order/findAll">Find all</a>
                    </div>
                </li>
            </c:if>



        </ul>
        <div class="dropdown my-lg-0 ml-2">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Category
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <c:forEach var="category" items="${sessionScope.categories}">
                        <a class="dropdown-item" href="/book/filter?id=${category.id}">${category.name}</a>
                    </c:forEach>
            </div>
        </div>
        <form action="/book/search" method="get" class="form-inline my-2 my-lg-0">
            <input name="query" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>