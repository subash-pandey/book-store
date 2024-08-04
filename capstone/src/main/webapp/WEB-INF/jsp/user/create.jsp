<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"></jsp:include>
<section style="background-color:gray">
    <div class="container">
        <div class="row pt-5 pb-5">
            <c:if test="${empty form.userId}">
                <h1 class="text-center">Create User</h1>
            </c:if>
            <c:if test="${not empty form.userId}">
                <h1 class="text-center">Edit User</h1>
            </c:if>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row pt-5">
            <div class="col-12">
                <form class="row g-3" action="/user/submit" method="get">
                    <input type="hidden" name="userId" value="${form.userId}">

                    <div class="col-md-6">
                        <label for="firstName" class="form-label">First Name</label>
                        <input type="text" class="form-control" id="FirstName" name="FirstName"
                               value="${form.firstName}">
                    </div>
                    <div class="col-md-6">
                        <label for="lastName" class="form-label">Last Name</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" value="${form.lastName}">
                    </div>
                    <div class="col-12">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" id="email" name="email" value="${form.email}" class="form-control
                            <c:if test ="${bindingResult.hasFieldErrors('email')}">is-invalid</c:if>">
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('email')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div class="text-danger">
                                    <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>

                    </c:if>
                    <div class="col-12">
                        <label for="password" class="form-label">Password</label>
                        <input type="password"  id="password" name="password" class="form-control
                          <c:if test ="${bindingResult.hasFieldErrors('password')}">is-invalid</c:if>">
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('password')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-12">
                                <div class="text-danger">
                                    <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>

                    </c:if>
                    <div class="col-12">
                        <label for="addressLine1" class="form-label">Address</label>
                        <input type="text" class="form-control" id="addressLine1" name="addressLine1"
                               placeholder="1234 Main St" value="${form.addressLine1}">
                    </div>
                    <div class="col-12">
                        <label for="addressLine2" class="form-label">Address 2</label>
                        <input type="text" class="form-control" id="addressLine2" name="addressLine2"
                               placeholder="Apartment, studio, or floor" value="${form.addressLine2}">
                    </div>
                    <div class="col-md-4">
                        <label for="city" class="form-label">City</label>
                        <input type="text" class="form-control" id="city" name="city" value="${form.city}">
                    </div>
                    <div class="col-md-2">
                        <label for="state" class="form-label">State</label>
                        <input type="text" class="form-control" id="state" name="state" value="${form.state}">
                    </div>

                    <div class="col-md-3">
                        <label for="zipCode" class="form-label">Zip Code</label>
                        <input type="text" class="form-control" id="zipCode" name="zipCode" value="${form.zipCode}">
                    </div>
                    <div class="col-md-3">
                        <label for="country" class="form-label">Country</label>
                        <input type="text" class="form-control" id="country" name="country" value="${form.country}">
                    </div>

                    <div class="col-md-6">
                        <label for="phone" class="form-label">Phone Number</label>
                        <input type="text" class="form-control" id="phone" name="phone" value="${form.phone}">
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
