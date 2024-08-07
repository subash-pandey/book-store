<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"></jsp:include>
<section class ="container">
    <div class ="row pt-5 pb-5">
        <h1>
            Book Search
        </h1>
    </div>

    <div class="container">
        <div class="row justify-content-center pt-5 pb-3">
            <div class="col-8 text-center">
                <form action ="/book/search">
                    <div class="mb-3">
                        <label for="search" class="form-label"><h4>Book Search</h4></label>
                        <input type="text" value ="${bookSearch}" class="form-control" id="search" name="bookSearch" placeholder="Search for books through title, author or ISBN ">
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>
        </div>
    </div>


</section>

<section>

</section>

<section style="background-color: white">
    <div class ="container">
        <div class ="row pt-5 pb-5">
            <h2 class ="text-center">
                Books found(${books.size()})
            </h2>
        </div>
    </div>
    <div class ="row pt-3">
        <div class ="col-12">
            <table class ="table">
                <tr>

                    <th>Book Id</th>
                    <th>Image</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Publisher</th>
                    <th>Available</th>

                </tr>

                <c:forEach items ="${books}" var ="book">
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