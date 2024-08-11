<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@700&display=swap" rel="stylesheet">

<link rel="stylesheet" href="/pub/style/index/index.css">


<jsp:include page="include/header.jsp"></jsp:include>

<div class="container text-center my-5">
    <h1 id="welcome" class="display-4" style="font-family: 'Playfair Display', serif; font-weight: 700;">Welcome to Book Store</h1>
    <p class="lead text-muted">Find and explore a wide range of books.</p>

    <div class="mt-4">
        <h4><a href="/book/search" class="btn btn-outline-primary btn-lg px-4">Search for Books</a></h4>
<sec:authorize access="!isAuthenticated()">
        <h4><a href="/user/login" class="btn btn-outline-secondary btn-lg px-4 mt-3">Login</a></h4>
        <h4><a href="/user/create" class="btn btn-outline-secondary btn-lg px-4 mt-3">Sign Up</a></h4>
</sec:authorize>
    </div>
</div>



<jsp:include page="include/footer.jsp"></jsp:include>