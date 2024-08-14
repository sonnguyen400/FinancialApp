
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.sonnguyen.individual.nhs.model.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sonnguyen.individual.nhs.constant.MemberShip" %>
<fmt:setLocale value="${sessionScope.lang}"  />
<fmt:setBundle basename="lang"/>


<jsp:useBean id="membership" scope="request" type="com.sonnguyen.individual.nhs.model.Membership"/>
<div class="row justify-content-center">
    <div class="col-lg-6">
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
                            <h3 class="text-white text-xl-10">Upto</h3>
                        </div>
                        <h3 class="text-white font-weight-semi-bold"><fmt:formatNumber value="${membership.loan_limit}"  currencyCode=","/> </h3>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="card">
            <div class="card-body">
                <form  method="post">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="DisbursementAccount">Disbursement Account</label>
                                <select name="disbursementAccountNumber" id="DisbursementAccount" class="form-control input-default">
                                    <c:forEach items="${requestScope.accounts}" var="account">
                                        <option value="${account.accountNumber}">${account.accountNumber}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="term">Term</label>
                                <select class="form-control" name="term_id" >
                                    <c:forEach var="setting" items="${requestScope.loanSettings}">
                                        <option value="${setting.id}">${setting.term} Month</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="amount">Amount</label>
                                <input type="number" name="amount" max="${membership.loan_limit}" min="100000" value="0" class="form-control">
                                <input name="LoanCreate" value="" type="hidden">
                                <input name="interestRate" value="10" type="hidden">
                                <div class="d-flex justify-content-end pt-3">
                                    <button type="submit" class="btn btn-primary">
                                        Continue
                                    </button>
                                </div>
                            </div>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
