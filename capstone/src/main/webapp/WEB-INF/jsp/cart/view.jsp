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
                            <form action="/cart/remove/${orderDetail.book.bookId}" method="post" style="display: inline;">
                                <input type="hidden" name="quantity" value="1"/>
                                <button type="submit" class="btn btn-danger">Remove 1</button>
                            </form>
                            <form action="/cart/add/${orderDetail.book.bookId}" method="post" style="display: inline;">
                                <input type="hidden" name="quantity" value="1"/>
                                <button type="submit" class="btn btn-success">Add 1</button>
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
    <div class="row pt-3">
        <div class="col-12 text-center">
            <form action="/cart/checkout" method="post">
                <button type="submit" class="btn btn-primary">Checkout</button>
            </form>
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