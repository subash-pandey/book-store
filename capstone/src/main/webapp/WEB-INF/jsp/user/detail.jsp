<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"></jsp:include>
<section>
    <div>
        <table class="table table-bordered table-hover">
            <tr><td>User Id</td><td>${user.userId}</td></tr>
            <tr><td>Name</td><td>${user.firstName} ${user.lastName}</td></tr>
            <tr><td>Address</td><td>${user.addressLine1}</td></tr>
            <tr><td></td><td>${user.addressLine2}</td></tr>
            <tr><td></td><td>${user.zipCode}</td></tr>
            <tr><td></td><td>${user.city}</td></tr>
            <tr><td></td><td>${user.state}</td></tr>
            <tr><td></td><td>${user.country}</td></tr>
            <tr><td>Phone</td><td>${user.phone}</td></tr>
        </table>

    </div>
</section>

<jsp:include page="../include/footer.jsp"></jsp:include>