<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"></jsp:include>

<section style="padding: 20px; background-color: #f9f9f9;">
    <div class="container">
        <div class="row mb-4">
            <div class="col-12">
                <h2 class="text-center">User Profile</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <table class="table table-striped table-hover">
                    <tr><th>User Id</th><td>${user.userId}</td></tr>
                    <tr><th>Name</th><td>${user.firstName} ${user.lastName}</td></tr>
                    <tr><th>Address</th><td>${user.addressLine1}</td></tr>
                    <tr><th></th><td>${user.addressLine2}</td></tr>
                    <tr><th>Zip Code</th><td>${user.zipCode}</td></tr>
                    <tr><th>City</th><td>${user.city}</td></tr>
                    <tr><th>State</th><td>${user.state}</td></tr>
                    <tr><th>Country</th><td>${user.country}</td></tr>
                    <tr><th>Phone</th><td>${user.phone}</td></tr>
                </table>
                <div class="text-center mt-4">
                    <a href="/user/profile/edit" class="btn btn-primary">Edit Profile</a>
                </div>
            </div>
        </div>
    </div>
</section>



<section style="padding: 20px; background-color: white;">
    <div class="container">
        <div class="row mb-4">
            <div class="col-12">
                <h2 class="text-center">Orders Completed (${orders.size()})</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <table class="table table-striped table-hover text-center">
                    <thead class="thead-light">
                    <tr>
                        <th>Order Id</th>
                        <th>Order Date</th>
                        <th>Total Amount</th>
                        <th>Order Details</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.orderDate}</td>
                            <td>${order.totalAmount}$</td>
                            <td><a href="/orderDetail/list/${order.orderId}" class="btn btn-outline-primary btn-sm">View</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp"></jsp:include>
