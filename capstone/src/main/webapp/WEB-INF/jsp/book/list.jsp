<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"></jsp:include>

<section style="padding: 20px; background-color: white;">
    <div class="container">
        <div class="row mb-4">
            <div class="col-12">
                <h2 class="text-center">Books Available (${books.size()})</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <table class="table table-striped table-hover text-center align-middle">
                    <thead class="thead-light">
                    <tr>
                        <th>Book Id</th>
                        <th>Image</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Publisher</th>
                        <th>Available</th>
                        <th>Add to Cart</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${books}" var="book">
                        <tr>
                            <td><a href="/book/index/${book.bookId}" class="text-decoration-none">${book.bookId}</a></td>
                            <td><img class="uniform-size" src="${book.pictureURL}" alt="${book.title}"/></td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td>${book.publisher}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${book.stock > 0}">
                                        ${book.stock}
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-danger">Out of stock</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${book.stock > 0}">
                                        <a href="/book/index/${book.bookId}" class="btn btn-outline-primary btn-sm">Add to Cart</a>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-muted">Unavailable</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp"></jsp:include>

<style>
    .uniform-size {
        width: 100px;
        height: 150px;
        object-fit: cover;
        border-radius: 5px;
    }
    .text-decoration-none {
        text-decoration: none;
    }
    .text-danger {
        color: #dc3545;
    }
    .text-muted {
        color: #6c757d;
    }
</style>
