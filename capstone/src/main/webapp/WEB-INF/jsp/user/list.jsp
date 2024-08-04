<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"></jsp:include>
<section style="background-color: white">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h2 class="text-center">Current Users (${users.size()})</h2>
        </div>
    </div>
    <div class="row pt-3">
        <div class="col-12">
            <table class="table table-bordered">
                <tr>
                    <th>User Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Order List</th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><a href ="/user/index/${user.userId}">${user.userId}</a></td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.phone}</td>
                        <td><a href="#">Order Details</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp"></jsp:include>