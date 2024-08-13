<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Book Store</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<section>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Book Store</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="/book/list">Books</a>
                    </li>
                    <sec:authorize access="!isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link" href="/user/login">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/user/create">Sign Up</a>
                        </li>
                    </sec:authorize>

                    <sec:authorize access="isAuthenticated()">
                        <sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
                            <li class="nav-item">
                                <a class="nav-link" href="/admin/dashboard">Admin Dashboard</a>
                            </li>
                        </sec:authorize>
                        <li class="nav-item">
                            <a class="nav-link" href="/user/profile">User Profile</a>
                        </li>
                    </sec:authorize>
                </ul>

                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <sec:authorize access="isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link me-3" href="/cart/view">Cart</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link me-3" href="/user/logout">Logout</a>
                        </li>
                        <li class="nav-item">
                            <span class="navbar-text align-middle">
                                Logged in as: <sec:authentication property="name" />
                            </span>
                        </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </nav>
</section>


