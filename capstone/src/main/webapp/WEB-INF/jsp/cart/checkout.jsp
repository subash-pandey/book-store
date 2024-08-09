<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <link rel="stylesheet" href="<c:url value='../resources/css/bootstrap.min.css' />">
    <script src="<c:url value='../resources/js/jquery.min.js' />"></script>
    <script src="<c:url value='../resources/js/bootstrap.bundle.min.js' />"></script>
    <script>
        $(document).ready(function() {
            // Toggle address form visibility
            $('#useExistingAddress').change(function() {
                if (this.checked) {
                    $('#existingAddressForm').show();
                    $('#newAddressForm').hide();
                }
            });

            $('#useNewAddress').change(function() {
                if (this.checked) {
                    $('#existingAddressForm').hide();
                    $('#newAddressForm').show();
                }
            });
        });
    </script>
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
<section style="background-color: white">
    <div class="container">
        <h2 class="text-center pt-5">Checkout</h2>

        <form action="<c:url value='../cart/checkout' />" method="post">
            <!-- Cart items and total amount -->
            <div class="row">
                <div class="col-md-12">
                    <h4>Order Details</h4>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Book Title</th>
                            <th>Quantity</th>
                            <th>Price</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${orderDetails}" var="detail">
                            <tr>
                                <td>${detail.book.title}</td>
                                <td>${detail.quantity}</td>
                                <td>${detail.price}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <h4>Total Amount: ${totalAmount}</h4>
                </div>
            </div>

            <!-- Address Selection -->
            <div class="form-group">
                <label>Select Address:</label><br>
                <input type="radio" id="useExistingAddress" name="addressType" value="existing" checked>
                <label for="useExistingAddress">Use existing address</label><br>
                <input type="radio" id="useNewAddress" name="addressType" value="new">
                <label for="useNewAddress">Provide new address</label>
            </div>

            <!-- Existing Address Form -->
            <div id="existingAddressForm" class="form-group">
                <label for="addressSelect">Select Address:</label>
                <select id="addressSelect" name="addressSelect" class="form-control">
                    <c:forEach items="${user.addresses}" var="address">
                        <option value="${address.id}">
                                ${address.addressLine1}, ${address.city}, ${address.state}, ${address.zipCode}, ${address.country}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <!-- New Address Form -->
            <div id="newAddressForm" class="form-group" style="display: none;">
                <h4>Enter New Address:</h4>
                <div class="form-group">
                    <label for="newAddressLine1">Address Line 1:</label>
                    <input type="text" id="newAddressLine1" name="shippingAddressLine1" class="form-control">
                </div>
                <div class="form-group">
                    <label for="newAddressLine2">Address Line 2:</label>
                    <input type="text" id="newAddressLine2" name="shippingAddressLine2" class="form-control">
                </div>
                <div class="form-group">
                    <label for="newCity">City:</label>
                    <input type="text" id="newCity" name="shippingCity" class="form-control">
                </div>
                <div class="form-group">
                    <label for="newState">State:</label>
                    <input type="text" id="newState" name="shippingState" class="form-control">
                </div>
                <div class="form-group">
                    <label for="newZipCode">Zip Code:</label>
                    <input type="text" id="newZipCode" name="shippingZipCode" class="form-control">
                </div>
                <div class="form-group">
                    <label for="newCountry">Country:</label>
                    <input type="text" id="newCountry" name="shippingCountry" class="form-control">
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Checkout</button>
        </form>
    </div>
</section>
<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>
