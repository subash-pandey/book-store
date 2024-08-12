<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"></jsp:include>

<!-- Book Search Section -->
<section class="container">
    <div class="row pt-5 pb-3">
        <h2 class="text-center" style="color: #333;">Book Search</h2>
    </div>

    <div class="row justify-content-center pb-5">
        <div class="col-md-8">
            <form action="/book/search" method="get">
                <div class="mb-3">
                    <label for="search" class="form-label"><h4 style="color: #555;">Find Your Book</h4></label>
                    <input type="text" value="${bookSearch}" class="form-control" id="search" name="bookSearch"
                           placeholder="Search by title, author, or ISBN" style="border-radius: 3px; border: 1px solid #ddd;">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary" style="border-radius: 3px; background-color: #007bff; border-color: #007bff;">
                        Search
                    </button>
                </div>
            </form>
        </div>
    </div>
</section>

<!-- Books Found Section -->
<section style="background-color: #f8f9fa; padding: 30px 0;">
    <div class="container">
        <div class="row pb-4">
            <h3 class="text-center" style="color: #333;">
                Books Found (${books.size()})
            </h3>
        </div>
        <div class="row">
            <div class="col-12">
                <table class="table table-striped table-hover">
                    <thead style="background-color: #007bff; color: white;">
                    <tr>
                        <th>Book ID</th>
                        <th>Image</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Publisher</th>
                        <th>Availability</th>
                        <th>Add to cart</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${books}" var="book">
                        <tr>
                            <td><a href="/book/index/${book.bookId}" style="color: #007bff;">${book.bookId}</a></td>
                            <td><img class="uniform-size" src="${book.pictureURL}" alt="${book.title}"></td>
                            <td>${book.title}</td>
                            <td>${book.author}</td>
                            <td>${book.publisher}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${book.stock > 0}">
                                        In Stock (${book.stock})
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color: red;">Out of Stock</span>
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
        width: 80px;  /* Reduced width for a more compact display */
        height: 120px; /* Reduced height for a more compact display */
        object-fit: cover; /* Ensures the image covers the set dimensions without distortion */
        border-radius: 3px; /* Adds a slight rounding to the corners */
        border: 1px solid #ddd; /* Adds a light border around the image */
    }
    .table-hover tbody tr:hover {
        background-color: #f1f1f1; /* Slight gray background on hover */
    }
</style>
