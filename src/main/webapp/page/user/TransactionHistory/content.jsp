<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.sonnguyen.individual.nhs.model.Transaction" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sonnguyen.individual.nhs.model.Account" %>

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
        <jsp:useBean id="transactions" scope="request" type="java.util.List"/>
        <jsp:useBean id="defaultAccount" scope="request" type="com.sonnguyen.individual.nhs.model.Account"/>
        <c:set var="defaultAccount" value="${defaultAccount}"/>
        <c:forEach items="${transactions}" var="transaction">
            <c:if test="${transaction.getAccountId()==defaultAccount.getId()}">
                <li class="list-group-item">
                    <div class="row align-items-center">
                        <span class="col-sm-1 text-md-11 pt-2 text-blue-700">

                            <i class="fi fi-sr-money-transfer-coin-arrow"></i>
        <%--                    <i class="fi fi-sr-wallet"></i>--%>
                        </span>
                        <div class="col">
                            <span class="text-dark text-md-6 mb-3">Transfer to</span>
                            <p> <c:out value="${transaction.getTransactionDate()}"/></p>
                        </div>
                        <div class="col-sm-1">
                            <h5>-<c:out value="${transaction.getValue()}"/></h5>
                            <span class="font-weight-semi-bold text-success">Success</span>
                        </div>
                    </div>
                </li>
            </c:if>
            <c:if test="${defaultAccount.getId()==transaction.getTransfer().getAccountId()}">
            <li class="list-group-item">
                <div class="row align-items-center">
                        <span class="col-sm-1 text-md-11 pt-2 text-yellow">
                             <i class="fi fi-sr-coins"></i>
                        </span>
                    <div class="col">
                        <span class="text-dark text-md-6 mb-3">Received</span>
                        <p> <c:out value="${transaction.getTransactionDate()}"/></p>
                    </div>
                    <div class="col-sm-1">
                        <h5><c:out value="${transaction.getValue()}"/></h5>
                        <span class="font-weight-semi-bold text-success">Success</span>
                    </div>
                </div>
            </li>
            </c:if>
        </c:forEach>
    </ul>
</div>