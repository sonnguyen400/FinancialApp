<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.sonnguyen.individual.nhs.model.Transaction" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sonnguyen.individual.nhs.model.Account" %>
<%@ page import="com.sonnguyen.individual.nhs.Constant.TransactionType" %>
<%@ page import="com.sonnguyen.individual.nhs.Constant.TransactionStatus" %>

<div class="form-group">
    <div class="input-group-prepend">
    <span class="input-group-text pr-2 pr-sm-3">
        From
    </span>
        <input type="date" class="form-control" id="startDate">
        <span class="input-group-text  pr-2 pr-sm-3" >
        To
    </span>
        <input type="date" class="form-control" id="endDate">
    </div>
</div>
<div class="card">
    <ul class="list-group">
        <jsp:useBean id="defaultAccount" scope="request" type="com.sonnguyen.individual.nhs.model.Account"/>
        <c:set var="defaultAccount" value="${defaultAccount}"/>
        <c:forEach items="${requestScope.transactions}" var="transaction">
            <li class="list-group-item">
                <div class="row align-items-center">
                       <span class="col-sm-1 text-md-11 pt-2 ">
                            <c:if test="${transaction.transactionType==TransactionType.TRANSFER.value}">
                                <i class="fi fi-sr-money-transfer-coin-arrow text-primary"></i>
                            </c:if>
                            <c:if test="${transaction.transactionType==TransactionType.RECEIVE.value}">
                                <i class="fi fi-sr-hand-holding-usd text-yellow"></i>
                            </c:if>
                            <c:if test="${transaction.transactionType==TransactionType.DEPOSIT.value}">
                               <i class="fi fi-sr-deposit text-info"></i>
                            </c:if>
                            <c:if test="${transaction.transactionType==TransactionType.DISBURSEMENT.value}">
                                <i class="fi fi-sr-expense-bill text-green"></i>
                            </c:if>
                       </span>
                    <div class="col">
                        <span class="text-dark text-md-6 mb-3">
                             <c:if test="${transaction.transactionType==TransactionType.TRANSFER.value}">
                                 Transfer
                             </c:if>
                            <c:if test="${transaction.transactionType==TransactionType.RECEIVE.value}">
                                Receive
                            </c:if>
                            <c:if test="${transaction.transactionType==TransactionType.DEPOSIT.value}">
                                Deposit
                            </c:if>
                            <c:if test="${transaction.transactionType==TransactionType.DISBURSEMENT.value}">
                                Disbursement
                            </c:if>
                        </span>
                        <div>
                            <span class="text-sm-4 text-lg-4">
                                <c:out value="${transaction.description}"/>
                            </span>
                        </div>
                        <p> <c:out value="${transaction.transactionAt}"/></p>
                    </div>
                    <div class="col-sm-1">
                        <h5>
                            <c:if test="${transaction.transactionType==TransactionType.RECEIVE.value or transaction.transactionType==TransactionType.DISBURSEMENT.value or transaction.transactionType==TransactionType.DEPOSIT.value}">
                                +
                            </c:if>
                            <c:if test="${transaction.transactionType==TransactionType.WITHDRAWAL.value or transaction.transactionType==TransactionType.TRANSFER.value or transaction.transactionType==TransactionType.PAYOFF.value }">
                                -
                            </c:if>
                            <fmt:formatNumber value="${transaction.amount}" currencyCode=","/>
                        </h5>
                        <c:if test="${transaction.status==TransactionStatus.SUCCESS.value}">
                            <span class="font-weight-semi-bold text-success">Success</span>
                        </c:if>
                        <c:if test="${transaction.status==TransactionStatus.PENDING.value}">
                            <span class="font-weight-semi-bold text-yellow">Pending</span>
                        </c:if>
                        <c:if test="${transaction.status==TransactionStatus.FAILURE.value}">
                            <span class="font-weight-semi-bold text-danger">Fail</span>
                        </c:if>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>