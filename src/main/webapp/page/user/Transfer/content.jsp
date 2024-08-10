<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.sonnguyen.individual.nhs.model.Account" %>
<%@ page import="java.util.List" %>
<%!
Object receiver_name;
%>
<%
    receiver_name= request.getAttribute("receiver_name");
%>
<div class="row justify-content-center">
    <div class="col col-sm-12 col-md-8 col-lg-6">
        <form id="form1" method="POST">
            <div class="row gutter-sm-2 gutter-lg-4">
                <div class="col-12">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                    <div class="select-custom" data-name="accountId">
                                        <div class="select d-none" >
                                            <div class="list-group list-group-flush">
                                                <c:forEach items="${requestScope.accounts}" var="account">
                                                    <div class="option list-group-item" data-value="${account.id}">
                                                        <div class="row">
                                                            <div class="col-12"><h4 class="text-primary font-weight-bold">${account.accountNumber}</h4></div>
                                                            <div class="col-12"><h5 class="text-primary"><fmt:formatNumber value="${account.balance}" currencyCode="."/></h5></div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body p-2">
                                    <div class="form-group col">
                                        <label for="account_number" class="col-form-label">
                                            <span>Receiver account number</span>
                                            <span class="text-danger">*</span>
                                        </label>
                                        <input type="text" data-rule="none" name="account_number" class="form-control input-default"
                                               id="receiver_account_number"
                                               value="${requestScope.account_number}">
                                    </div>
                                    <div class="form-group col">
                                        <label for="amount" class="col-form-label">
                                            <span>Amount</span>
                                            <span class="text-danger">*</span>
                                        </label>
                                        <input type="number" data-rule="required" min=0 name="amount" class="form-control input-default"
                                               value="${requestScope.amount}">
                                    </div>
                                    <div class="form-group col">
                                        <label for="message" class="col-form-label">
                                            <span>Message</span>
                                            <span class="text-danger">*</span>
                                        </label>
                                        <input type="text" data-rule="none" name="message" class="form-control input-default"
                                               value="${requestScope.message}">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body p-2">
                                    <form id="form2">
                                        <div class="form-group col">
                                            <label id="current-balance col-form-label">Receiver Information</label>
                                            <input data-rule="required" style="font-size: 18px;color: gray;text-transform: uppercase;letter-spacing: 2px" class="input-default form-control"   disabled id="receiver_name">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="d-flex justify-content-end">
                        <button type="submit" class="btn btn-primary" >Continue</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
