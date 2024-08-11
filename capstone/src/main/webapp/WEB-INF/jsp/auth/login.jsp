<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<!-- Page Header -->
<section style="background-color: #f8f9fa; padding: 40px 0;">
    <div class="container">
        <div class="row">
            <h3 class="text-center" style="color: #333;">Please Log In</h3>
        </div>
    </div>
</section>

<!-- Main Content -->
<section style="padding: 30px 0;">
    <div class="container">
        <c:if test="${param['error'] eq ''}">
            <div class="row">
                <div class="col-12">
                    <div class="alert alert-danger text-center" role="alert">Invalid Username or Password</div>
                </div>
            </div>
        </c:if>

        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-4">
                <div style="border: 1px solid #ddd; padding: 20px; border-radius: 5px;">
                    <form action="/user/loginPost" method="post">

                        <!-- Email Input -->
                        <div class="mb-3">
                            <label for="usernameId" class="form-label">Email</label>
                            <input type="text" id="usernameId" name="username"
                                   class="form-control <c:if test='${bindingResult.hasFieldErrors("email")}'>is-invalid</c:if>"
                                   value="${form.email}" style="border-radius: 3px;">
                            <c:if test="${bindingResult.hasFieldErrors('email')}">
                                <div class="text-danger mt-1">
                                    <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </c:if>
                        </div>

                        <!-- Password Input -->
                        <div class="mb-3">
                            <label for="passwordId" class="form-label">Password</label>
                            <input type="password" id="passwordId" name="password"
                                   class="form-control <c:if test='${bindingResult.hasFieldErrors("password")}'>is-invalid</c:if>"
                                   value="${form.password}" style="border-radius: 3px;">
                            <c:if test="${bindingResult.hasFieldErrors('password')}">
                                <div class="text-danger mt-1">
                                    <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </c:if>
                        </div>

                        <!-- Submit Button -->
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary" style="border-radius: 3px; background-color: #007bff; border-color: #007bff;">
                                Log In
                            </button>
                        </div>

                    </form>
                </div>
            </div>
        </div>

        <!-- Sign Up Link -->
        <div class="row justify-content-center pt-4">
            <div class="col-auto">
                <a href="/user/create" class="btn btn-outline-secondary" style="border-radius: 3px;">Sign Up if you are a new user</a>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp" />
