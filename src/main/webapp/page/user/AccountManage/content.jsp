<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row justify-content-between">
    <div class="col-lg-4 col-md-6 col-sm-12">
        <div class="card">
            <div class="card-body">
                <div class="card-title">Sum of balance</div>
                <h3 class="text-primary">
                    <fmt:formatNumber value="${requestScope.sum}" currencySymbol="," currencyCode="vnd"/>
                </h3>
            </div>
        </div>
    </div>
    <div class="col-lg-8 col-md-6 col-sm-12">
        <div class="card">
            <div class="card-body">
                <div class="card-title">
                    Personal Account
                </div>
                <div class="row">
                    <c:forEach var="account" items="${requestScope.accounts}">
                        <div class="col-lg-6 col-sm-12">
                            <div class="border-solid border-white-gray pt-3 pb-2 px-2 radius">
                                <div class="p-2">
                                    <h4 class="card-subtitle text-secondary">Principle Account</h4>
                                    <h5 class="text-blue-700 py-3">${account.accountNumber}</h5>
                                    <div class="divider"></div>
                                    <div class="d-flex justify-content-between">
                                        <h4 class="text-primary"><fmt:formatNumber value="${account.balance}" currencyCode="," currencySymbol="vnd"/></h4>
                                        <a href="<%=request.getContextPath()%>/app/account?id=${account.id}" class="btn btn-rounded bg-primary text-white">Detail</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="col-lg-6 col-sm-12">
                        <a href="${pageContext.request.contextPath}/app/account/new" class="border-solid border-white-gray pt-3 pb-2 px-2 radius">
                            <div class="p-2">
                                <div class="d-flex flex-column align-items-center">
                                    <div style="font-size: 4rem"><i class="fi fi-sr-square-plus"></i></div>
                                    <div>Create new Account</div>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="card-title">
                    Coop Accounts
                </div>
                <div class="row">
                    <c:forEach var="account" items="${requestScope.coopAccounts}">
                        <div class="col-lg-6 col-sm-12">
                            <div class="border-solid border-white-gray pt-3 pb-2 px-2 radius">
                                <div class="p-2">
                                    <h4 class="card-subtitle text-secondary">Principle Account</h4>
                                    <h5 class="text-blue-700 py-3">${account.accountNumber}</h5>
                                    <div class="divider"></div>
                                    <div class="d-flex justify-content-between">
                                        <h4 class="text-primary"><fmt:formatNumber value="${account.balance}" currencyCode="," currencySymbol="vnd"/></h4>
                                        <a href="<%=request.getContextPath()%>/app/account?id=${account.id}" class="btn btn-rounded bg-primary text-white">Detail</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

</div>
