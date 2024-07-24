<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.sonnguyen.individual.nhs.Constant.SavingType" %>
<%@ page import="com.sonnguyen.individual.nhs.Constant.Rollover" %>
<%@ page import="com.sonnguyen.individual.nhs.Utils.RequestUtils" %>

<%@ page import="com.sonnguyen.individual.nhs.Constant.MemberShip" %>
<jsp:useBean id="membership" scope="request" class="com.sonnguyen.individual.nhs.Model.Membership"/>
<div class="row">
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
                            <h3 class="text-white text-xl-10">Savings upto</h3>
                        </div>
                        <h3 class="text-white font-weight-semi-bold">${membership.saving_limit}</h3>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="card gradient-8">
            <div class="card-body">
                <div class="row justify-content-between">
                    <div class="col">
                        <p class="text-sm-6 mb-1 opacity-7">Amount</p>
                        <h4 class="text-white" id="amountRef">0</h4>
                    </div>
                    <div class="col d-flex flex-column align-items-end">
                        <p class="text-sm-6 mb-1 opacity-7">Interest Rate</p>
                        <h6 class="text-white font-weight-semi-bold" id="interest_rate_ref">5% per year</h6>
                        <i class="fi fi-sr-budget-alt text-sm-12 opacity-5"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col">
        <div class="card">
            <div class="card-body">
                <form  method="post" action="${requestScope['javax.servlet.forward.request_uri']}">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="sourceAccount">Source Account</label>
                                <select name="sourceAccount" id="sourceAccount" class="form-control input-default">
                                    <c:forEach items="${requestScope.accounts}" var="account">
                                        <option value="${account.id}">${account.accountNumber}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="savingType">Type</label>
                                <select name="type" id="savingType" class="form-control input-default">
                                    <option value="<%=SavingType.TERM_DEPOSIT.value%>">Term deposit</option>
                                    <option value="<%=SavingType.DEMAND_DEPOSIT.value%>">Demand deposit</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="term">Term</label>
                                <select class="form-control" id="term" name="term" >
                                    <option value="6">6 Months</option>
                                    <option value="12">12 Months</option>
                                </select>
                            </div>

                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="rollover">Roll over</label>
                                <select name="rollover" id="rollover" class="form-control input-default">
                                    <option value="<%=Rollover.WITHDRAW_ENTIRE.value%>">Withdraw Entire</option>
                                    <option value="<%=Rollover.ROLLOVER_ALL.value%>">Rollover all</option>
                                    <option value="<%=Rollover.ROLLOVER_PRINCIPAL.value%>">Demand deposit</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="amount">Amount</label>
                                <input type="number" name="amount" min="100000" value="0" class="form-control">
                                <input name="interestRate" value="5" type="hidden">
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