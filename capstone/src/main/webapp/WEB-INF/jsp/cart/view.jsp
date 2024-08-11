<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"></jsp:include>
<section style="background-color: white">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h2 class="text-center">Your Cart (${orderDetails.size()})</h2>
        </div>
    </div>
    <div class="row pt-3">
        <div class="col-12">
            <table class="table table-bordered text-center align-middle">
                <tr>
                    <th>Book Id</th>
                    <th>Image</th>
                    <th>Title</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Actions</th>
                </tr>
                <c:forEach items="${orderDetails}" var="orderDetail">
                    <tr>
                        <td>${orderDetail.book.bookId}</td>
                        <td><img class="uniform-size" src="${orderDetail.book.pictureURL}" alt="Book Image"/></td>
                        <td>${orderDetail.book.title}</td>
                        <td>${orderDetail.quantity}</td>
                        <td>${orderDetail.price}</td>
                        <td>${orderDetail.price * orderDetail.quantity}</td>
                        <td>
                            <form action="/cart/remove/${orderDetail.book.bookId}" method="get" style="display: inline;">
                                <button type="submit" class="btn btn-danger">Remove</button>
                            </form>
                            <form action="/book/index/${orderDetail.book.bookId}" method="get" style="display: inline;">
                                <button type="submit" class="btn btn-success">Add </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="4"></td>
                    <td><strong>Total:</strong></td>
                    <td>
                        <strong>
                            <c:set var="totalAmount" value="0.0"/>
                            <c:forEach items="${orderDetails}" var="orderDetail">
                                <c:set var="totalAmount" value="${totalAmount + (orderDetail.price * orderDetail.quantity)}"/>
                            </c:forEach>
                            ${totalAmount}
                        </strong>
                    </td>
                    <td></td>
                </tr>
            </table>
        </div>
    </div>
    <div>
        <h4>Please choose the address for shipping</h4>
    </div>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="addressOption" id="userAddress">
        <label class="form-check-label" for="userAddress">
           User provided Address
        </label>
    </div>
    <div class="form-check">
        <input class="form-check-input" type="radio" name="addressOption" id="newAddress" >
        <label class="form-check-label" for="newAddress">
           New Address
        </label>
    </div>
    <div class="row pt-3">
        <div class="col-12 text-center">
            <form action="/cart/checkoutSuccess" method="post" class="row g-3">
                <div class="col-md-6">
                    <label for="addressLine1" class="form-label">Address Line 1</label>
                    <input type="text" class="form-control" id="addressLine1" name="shippingAddressLine1"
                           placeholder="1234 Main St" value="${user.addressLine1}">
                </div>
                <div class="col-md-6">
                    <label for="addressLine2" class="form-label">Address Line 2</label>
                    <input type="text" class="form-control" id="addressLine2" name="shippingAddressLine2"
                           placeholder="Apartment, studio, or floor" value="${user.addressLine2}">
                </div>
                <div class="col-md-6">
                    <label for="city" class="form-label">City</label>
                    <input type="text" class="form-control" id="city" name="shippingCity" value="${user.city}">
                </div>
                <div class="col-md-2">
                    <label for="state" class="form-label">State</label>
                    <input type="text" class="form-control" id="state" name="shippingState" value="${user.state}">
                </div>
                <div class="col-md-2">
                    <label for="zipCode" class="form-label">Zip Code</label>
                    <input type="text" class="form-control" id="zipCode" name="shippingZipCode" value="${user.zipCode}">
                </div>
                <div class="col-md-2">
                    <label for="country" class="form-label">Country</label>
                    <input type="text" class="form-control" id="country" name="shippingCountry" value="${user.country}">
                </div>

                <div class="col-12 text-center pt-3">
                    <button type="submit" class="btn btn-primary" <c:if test="${orderDetails.size()== 0}">disabled</c:if>>Checkout</button>
                </div>
            </form>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp"></jsp:include>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const newAddressRadio = document.getElementById("newAddress");
        const userAddressRadio = document.getElementById("userAddress");
        const formFields = ["addressLine1", "addressLine2", "city", "state", "zipCode", "country"];

        // User's saved address values
        const userAddress = {
            addressLine1: "${user.addressLine1}",
            addressLine2: "${user.addressLine2}",
            city: "${user.city}",
            state: "${user.state}",
            zipCode: "${user.zipCode}",
            country: "${user.country}"
        };

        // Function to populate form fields with user's address
        function populateFields(address) {
            formFields.forEach(function (field) {
                document.getElementById(field).value = address[field];
            });
        }

        // Function to clear form fields
        function clearFields() {
            formFields.forEach(function (field) {
                document.getElementById(field).value = "";
            });
        }

        // Event listener for new address radio button
        newAddressRadio.addEventListener("change", function () {
            if (this.checked) {
                clearFields();
            }
        });

        // Event listener for user address radio button
        userAddressRadio.addEventListener("change", function () {
            if (this.checked) {
                populateFields(userAddress);
            }
        });

        // Initially populate fields with user address if "User provided Address" is selected by default
        if (userAddressRadio.checked) {
            populateFields(userAddress);
        }
    });
</script>

<style>
    .uniform-size {
        width: 100px;
        height: 150px;
        object-fit: cover;
    }

    .form-control {
        margin-bottom: 10px;
    }

    .form-label {
        margin-top: 10px;
    }

    .row.g-3 {
        row-gap: 1rem;
    }
</style>
