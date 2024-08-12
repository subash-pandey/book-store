<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"></jsp:include>

<!-- Book Detail Section -->
<section style="background-color: #f8f9fa;">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h2 class="text-center" style="color: #333;">Book Detail</h2>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-8">
                <c:if test="${not empty book}">
                    <div class="card mb-3" style="border: none;">
                        <div class="row no-gutters">
                            <div class="col-md-4">
                                <img src="${book.pictureURL}" class="card-img uniform-size" alt="${book.title}" style="border-radius: 5px; border: 1px solid #ddd;">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="card-title" style="color: #555;">${book.title}</h5>
                                    <p class="card-text" style="color: #777;">Author: ${book.author}</p>
                                    <p class="card-text" style="color: #777;">Publisher: ${book.publisher}</p>
                                    <p class="card-text" style="color: #777;">Price: $${book.price}</p>
                                    <form action="/cart/add/${book.bookId}" method="get">
                                        <div class="form-group">
                                            <label for="quantity" style="color: #555;">Quantity:</label>
                                            <input style="width:80px; border-radius: 3px; border: 1px solid #ddd;"
                                                   type="number" id="quantity" name="quantity"
                                                   class="form-control" max="${book.stock}" min="0" value="0">
                                        </div>
                                        <button id="addToCartButton" type="submit" class="btn btn-primary mt-3"
                                                style="border-radius: 3px; background-color: #007bff; border-color: #007bff;"
                                                <c:if test="${book.stock <= 0}">disabled</c:if>>Add to Cart</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty book}">
                    <div class="alert alert-danger text-center" role="alert" style="border-radius: 5px;">
                        Book not found.
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</section>

<!-- Search for More Books Section -->
<section style="background-color: #fff;">
    <div class="container text-center my-5">
        <h4><a href="/book/search" class="btn btn-outline-secondary btn-lg px-4 mt-3"
               style="border-radius: 3px;">Search for more</a></h4>
    </div>
</section>

<jsp:include page="../include/footer.jsp"></jsp:include>

<!-- Minimalist Styling -->
<style>
    .uniform-size {
        width: 100%;
        height: auto;
        object-fit: cover;
        border-radius: 5px;
        border: 1px solid #ddd;
    }
</style>

<!-- Script for Quantity and Button Control -->
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const quantityInput = document.getElementById('quantity');
        const addToCartButton = document.getElementById('addToCartButton');

        // Function to enable or disable the button based on the quantity value
        function toggleAddToCartButton() {
            addToCartButton.disabled = quantityInput.value <= 0;
        }

        // Initialize the button state when the page loads
        toggleAddToCartButton();

        // Add event listener to monitor changes in the quantity input field
        quantityInput.addEventListener('input', toggleAddToCartButton);
    });
</script>
