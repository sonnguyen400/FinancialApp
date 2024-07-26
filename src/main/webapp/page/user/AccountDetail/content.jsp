<%@ page import="org.springframework.http.HttpStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ex" uri="/WEB-INF/custom.tld"%>
<jsp:useBean id="account" scope="request" type="com.sonnguyen.individual.nhs.model.Account"/>
<c:if test="${requestScope.account!=null}">
    <div class="row  justify-content-center">
        <div class="col-ssm-12 col-md-8 col-lg-5">
            <div class="card">
                <div class="pt-3 pb-2 px-3 d-flex flex-row justify-content-between align-top align-top">
                    <div >
                        <div class="sub-title">Balance</div>
                        <div class="card-title"><h3 class="text-primary"><fmt:formatNumber value="${account.balance}"/></h3></div>
                    </div>
                    <ex:accountTier value="${account.tier.id}"/>
                </div>
            </div>
        </div>
    </div>
    <div class="row  justify-content-center">
        <div class="col-ssm-12 col-md-8 col-lg-5">
            <div class="card">
                <div class="card-body">
                    <div class="d-flex py-2 flex-row justify-content-between">
                        <span >Account Number</span>
                        <span>${account.accountNumber}</span>
                    </div>
                    <div class="d-flex py-2 flex-row justify-content-between">
                        <span >Account Status</span>
                        <span>
                            <c:if test="${account.status==1}">Open</c:if>
                            <c:if test="${account.status==2}">Inactive</c:if>
                            <c:if test="${account.status==3}">Closed</c:if>
                            <c:if test="${account.status==4}">Overdrawn</c:if>
                            <c:if test="${account.status==5}">Frozen</c:if>
                            <c:if test="${account.status==6}">Pending</c:if>
                        </span>
                    </div>
                    <div class="d-flex py-2 flex-row justify-content-between">
                        <span >Overdraft Limit</span>
                        <span>${account.tier.overdraftLimit}</span>
                    </div>
                    <div class="d-flex py-2 flex-row justify-content-between">
                        <span >Limit Transaction</span>
                        <span>${account.tier.limitTransaction}</span>
                    </div>
                    <div class="d-flex py-2 flex-row justify-content-between">
                        <span>Type</span>
                        <span>
                            <c:if test="${account.accountType==1}">
                                Principal
                            </c:if>
                            <c:if test="${account.accountType==2}">
                                General
                            </c:if>
                            <c:if test="${account.accountType==3}">
                                Savings
                            </c:if>
                        </span>
                    </div>
                    <div class="row pt-4">
                        <c:if test="${account.status!=2}">
                            <div class="col"><a class="btn btn-block bg-primary text-white">Freeze</a></div>
                        </c:if>
                        <c:if test="${account.status!=5}">
                            <div class="col"><a class="btn btn-block bg-danger text-white">Inactive</a></div>
                        </c:if>
                    </div>
                </div>
            </div>

        </div>
    </div>
</c:if>
<c:if test="${requestScope.account==null}">
    <%
        response.sendError(HttpStatus.NOT_FOUND.value());
    %>
</c:if>
