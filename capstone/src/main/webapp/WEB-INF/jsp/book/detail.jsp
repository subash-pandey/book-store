<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"></jsp:include>
<section style="background-color: white">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h2 class="text-center">Book Detail</h2>
        </div>
        <div class="row pt-3">
            <div class="col-12">
                <c:if test="${not empty book}">
                    <div class="card mb-3">
                        <div class="row no-gutters">
                            <div class="col-md-4">
                                <img src="${book.pictureURL}" class="card-img uniform-size" alt="${book.title}">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="card-title">${book.title}</h5>
                                    <p class="card-text">Author: ${book.author}</p>
                                    <p class="card-text">Publisher: ${book.publisher}</p>
                                    <p class="card-text">Price: $${book.price}</p>
                                    <form action="/cart/add/${book.bookId}" method="get">
                                        <div class="form-group">
                                            <label for="quantity">Quantity:</label>
                                            <input style="width:80px" type="number" id="quantity" name="quantity" class="form-control" max="${book.stock}" min="0" value="1">
                                        </div>

                                        <button id="addToCartButton" type="submit" class="btn btn-primary mt-3"
                                                <c:if test="${book.stock <= 0}">disabled</c:if>>Add to Cart</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty book}">
                    <div class="alert alert-danger" role="alert">
                        Book not found.
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../include/footer.jsp"></jsp:include>

<style>
    .uniform-size {
        width: 100%;
        height: auto;
        object-fit: cover;
    }
</style>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const quantityInput = document.getElementById('quantity');
        const addToCartButton = document.getElementById('addToCartButton');

        // Function to enable or disable the button based on the quantity value
        function toggleAddToCartButton() {
            if (quantityInput.value <= 0) {
                addToCartButton.disabled = true;
            } else {
                addToCartButton.disabled = false;
            }
        }

        // Initialize the button state when the page loads
        toggleAddToCartButton();

        // Add event listener to monitor changes in the quantity input field
        quantityInput.addEventListener('input', toggleAddToCartButton);
    });
</script>
