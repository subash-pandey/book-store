<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"></jsp:include>
<section style="background-color: white">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h2 class="text-center">Books Available (${books.size()})</h2>
        </div>
    </div>
    <div class="row pt-3">
        <div class="col-12">
            <table class="table table-bordered text-center align-middle">
                <tr>

                    <th>Book Id</th>
                    <th>Image</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Publisher</th>
                    <th>Available</th>

                </tr>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td><a href ="/user/index/${book.bookId}">${book.bookId}</a></td>
                        <td><img class="uniform-size" src = "${book.pictureURL}"/></td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.publisher}</td>
                        <td>
                            <c:choose>
                                <c:when test="${book.stock > 0}">
                                    ${book.stock}
                                </c:when>
                                <c:otherwise>
                                    Out of stock
                                </c:otherwise>
                            </c:choose>
                        </td>

                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp"></jsp:include>

<style>
    .uniform-size {
        width: 100px;  /* Set the width you want */
        height: 150px; /* Set the height you want */
        object-fit: cover; /* This ensures the image covers the set dimensions without distortion */
    }
</style>