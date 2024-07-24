<%@ page import="com.sonnguyen.individual.nhs.Constant.MemberShip" %>
<%@ taglib prefix="tilex" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="membership" scope="request" type="com.sonnguyen.individual.nhs.Model.Membership"/>
<div class="row">
    <div class="col-lg-4">
        <c:forEach var="account" items="${requestScope.accounts}">
            <div class="row">
                <a href="<%=request.getContextPath()%>/app/saving/account?id=${account.id}" class="card w-100">
                    <div class="card-body">
                        <div class="row justify-content-between align-items-center">
                            <div class="col">
                                <div class="card-title">
                                    <fmt:formatNumber value="${account.balance}" type="currency" currencySymbol=","/>
                                </div>
                                <div class="card-subtitle">
                                    <span>${account.accountNumber}</span>
                                </div>
                            </div>
                            <i class="fi fi-sr-angle-small-right"></i>
                        </div>
                    </div>
                </a>
            </div>
        </c:forEach>

    </div>
    <div class="col-lg-8">

        <div class="row">
            <div class="col-12 w-100">
                <div class="card gradient-10 text-white">
                    <div class="card-body">
                        <div class="col mb-3">
                            <p class="mb-1 opacity-5">Membership</p>
                            <h5 class="text-white font-weight-semi-bold">
                                <c:if test="${membership.id==MemberShip.STANDARD.value}">
                                    <div><span class="badge badge-light">${membership.name}</span></div>
                                </c:if>
                                <c:if test="${membership.id==MemberShip.DIAMOND.value}">
                                    <div><span class="badge badge-light">${membership.name}</span></div>
                                </c:if>
                                <c:if test="${membership.id==MemberShip.GOLD.value}">
                                    <div><span class="badge badge-light">${membership.name}</span></div>
                                </c:if>
                            </h5>
                        </div>
                        <div class="row align-items-center">
                            <div class="col align-items-center text-center">

                                <div class="d-flex justify-content-center">
                                    <span class="text-lg-10 pr-3 opacity-5 pb-2"><i class="fi fi-sr-usd-circle"></i></span>
                                    <h3 class="text-white text-xl-10">Savings upto</h3>
                                </div>
                                <h3 class="text-white font-weight-semi-bold"><fmt:formatNumber value="${membership.saving_limit}" currencyCode="vnd" currencySymbol="vnd"/></h3>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <a href="${pageContext.request.contextPath}/app/saving/create" class="col-ssm-6">
                    <div class="card react-widget gradient-3">
                        <div class="gutter-ssm-2">
                            <div class="col">
                                <h4 class="text-white text-md-8">Create saving account</h4>
                                <span class="float-right text-ssm-11 opacity-5 "><i
                                        class="fi fi-sr-money-bill-transfer"></i></span>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>