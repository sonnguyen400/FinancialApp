<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ex" uri="/WEB-INF/custom.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.sonnguyen.individual.nhs.constant.SavingType" %>
<%@ page import="com.sonnguyen.individual.nhs.constant.Rollover" %>
<fmt:setLocale value="${sessionScope.lang}"  />
<fmt:setBundle basename="lang"/>
<jsp:useBean id="membership" scope="request" class="com.sonnguyen.individual.nhs.model.Membership"/>
<c:if test="${requestScope.alert!=null}">
    <jsp:useBean id="alert" scope="request" class="com.sonnguyen.individual.nhs.dto.Alert"/>
    <ex:alert type="${alert.type}" link="${alert.link}" href="${alert.href}">${alert.message}</ex:alert>
</c:if>
<div class="row justify-content-center">
    <div class="col col-sm-12 col-md-12 col-lg-6 col-xl-4">
        <div class="row">
            <div class="col-12">
                <div class="card gradient-10 text-white">
                    <div class="card-body">
                        <div class="col mb-3">
                            <p class="mb-1 opacity-5">Membership</p>
                            <h5 class="text-white font-weight-semi-bold">
                                <ex:membership value="${membership.id}"/>
                            </h5>
                        </div>
                        <div class="row align-items-center">
                            <div class="col align-items-center text-center">

                                <div class="d-flex justify-content-center">
                                    <span class="text-lg-10 pr-3 opacity-5 pb-2"><i class="fi fi-sr-usd-circle"></i></span>
                                    <h3 class="text-white text-xl-10">Savings upto</h3>
                                </div>
                                <h3 class="text-white font-weight-semi-bold"><fmt:formatNumber value="${membership.saving_limit}" currencyCode="."/> </h3>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <form  method="post">
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-group">
                                        <label for="sourceAccount">Source Account</label>
                                        <div class="form-control h-fit">
                                            <div  class="select-custom" data-name="sourceAccount">
                                                <div  class="select card d-none">
                                                    <div class="list-group card-body list-group-flush">
                                                        <c:forEach items="${requestScope.accounts}" var="account">
                                                            <div class="option list-group-flush" data-value="${account.id}">
                                                                <div class="text-primary">${account.accountNumber}</div>
                                                                <div class="text-black-50"><fmt:formatNumber value="${account.balance}" currencyCode="."/> </div>
                                                            </div>
                                                            <hr>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <label for="savingType">Type</label>
                                        <select name="type" id="savingType" class="form-control input-default">
                                            <option value="<%=SavingType.TERM_DEPOSIT.value%>">Term deposit</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="term">Term</label>
                                        <div class="form-control h-fit" >
                                            <div class="select-custom" data-name="term_id">
                                                <div  class="select card d-none">
                                                    <div class="list-group card-body list-group-flush">
                                                        <c:forEach items="${requestScope.savingsSettings}" var="savings">
                                                            <div class="option list-group-flush" data-value="${savings.id}">
                                                                <div class="text-primary">${savings.term}</div>
                                                                <div class="text-black-50">${savings.interestRate}% per Year</div>
                                                            </div>
                                                            <hr>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="rollover">Rollover</label>
                                        <select name="rollover" id="rollover" class="form-control input-default">
                                            <option value="<%=Rollover.WITHDRAW_ENTIRE.value%>">Withdraw Entire</option>
                                            <option value="<%=Rollover.ROLLOVER_ALL.value%>">Rollover all</option>
                                            <option value="<%=Rollover.ROLLOVER_PRINCIPAL.value%>">Rollover Principal</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Default Beneficiary Account</label>
                                        <select class="form-control" name="beneficiary_account_id">
                                            <c:forEach var="account" items="${requestScope.accounts}">
                                                <option value="${account.id}">${account.accountNumber}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="amount">Amount</label>
                                        <input type="number" name="amount" min="100000" value="0" class="form-control">
                                    </div>
                                    <div class="d-flex justify-content-end pt-3">
                                        <button type="submit" class="btn btn-primary">
                                            Continue
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>