<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"></jsp:include>
<section style="background-color:gray">
    <div class="container">
        <div class="row pt-5 pb-5">
            <c:if test="${empty form.bookId}">
                <h1 class="text-center">Add Book</h1>
            </c:if>
            <c:if test="${not empty form.bookId}">
                <h1 class="text-center">Edit Book Detail</h1>
            </c:if>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row pt-5">
            <div class="col-12">
                <form class="row g-3" action="/book/submit" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="bookId" value="${form.bookId}">

                    <div class="col-md-6">
                        <label for="title" class="form-label">Title of the book</label>
                        <input type="text" class="form-control" id="title" name="title" value="${form.title}" >
                    </div>
                    <div class="col-md-6">
                        <label for="author" class="form-label">Author</label>
                        <input type="text" class="form-control" id="Author" name="author" value="${form.author}">
                    </div>
                    <div class="col-md-6">
                        <label for="genre" class="form-label">Genre</label>
                        <input type="text" class="form-control" id="genre" name="genre" value="${form.genre}">
                    </div>
                    <div class="col-md-6">
                        <label for="price" class="form-label">Price</label>
                        <input type="text" class="form-control" id="price" name="price" value="${form.price}">
                    </div>
                   <div class="col-md-6">
                        <label for="stock" class="form-label">Number of books in stock</label>
                        <input type="text" class="form-control" id="stock" name="stock"  value="${form.stock}">
                    </div>
                    <div class="col-md-6">
                        <label for="isbn" class="form-label">ISBN </label>
                        <input type="text" class="form-control" id="isbn" name="isbn"  value="${form.isbn}">
                    </div>
                    <div class="col-12">
                        <label for="description" class="form-label">Description</label>
                        <textarea class="form-control" id="description" name="description" rows="3"> ${form.description}
                        </textarea>
                    </div>
                    <div class="col-md-6">
                        <label for="publisher" class="form-label">Publisher</label>
                        <input type="text" class="form-control" id="publisher" name="publisher" value="${form.publisher}">
                    </div>

                    <div class="col-md-6">
                        <label for="publishedDate" class="form-label">Published Date</label>
                        <input type="date" class="form-control" id="publishedDate" name="publishedDate" value="${form.publishedDate}">
                    </div>
                    <div class="col-12">
                        <label for="image" class="form-label">Upload image of the book</label>
                        <input type="file" class="form-control" id="image" name="image" >
                    </div>
                    <div class="col-12">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp"></jsp:include>
