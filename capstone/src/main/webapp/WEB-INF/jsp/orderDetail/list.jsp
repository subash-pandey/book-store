<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"></jsp:include>
<section class="order-details-section">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h2 class="text-center">Order Details  (${orderDetails.size()})</h2>
        </div>
    </div>
    <div class="row pt-3">
        <div class="col-12">
            <table class="table table-bordered text-center align-middle">
                <thead>
                <tr>
                    <th>Book Id</th>
                    <th>Image</th>
                    <th>Title</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orderDetails}" var="orderDetail">
                    <tr>
                        <td>${orderDetail.book.bookId}</td>
                        <td><img class="uniform-size" src="${orderDetail.book.pictureURL}" alt="${orderDetail.book.title} Image"/></td>
                        <td>${orderDetail.book.title}</td>
                        <td>${orderDetail.quantity}</td>
                        <td>${orderDetail.price}</td>
                        <td>${orderDetail.price * orderDetail.quantity}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="4"></td>
                    <td><strong>Total:</strong></td>
                    <td><strong>${order.totalAmount}</strong></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp"></jsp:include>
