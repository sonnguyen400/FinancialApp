<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.sonnguyen.individual.nhs.model.Loan" %>
<jsp:useBean id="loans" scope="request" type="java.util.List"/>
<%
    if(!loans.isEmpty()) request.setAttribute("newest",loans.get(0));
%>
<div class="row">
    <div class="col-lg-9">
        <jsp:useBean id="newest" scope="request" class="com.sonnguyen.individual.nhs.model.Loan"/>
        <c:if test="${loans.size()>0}">
            <div class="card gradient-10 text-white">
                <div class="card-body">
                    <div class="col mb-3">
                        <p class="mb-1 opacity-5">
                            ${newest.createAt}
                        </p>
                        <h5 class="text-white font-weight-semi-bold">
                            ${newest.term} Month
                        </h5>
                        <h5 class="text-white font-weight-semi-bold">
                            ${newest.disbursementAccountNumber}
                        </h5>
                    </div>
                    <div class="row align-items-center">
                        <div class="col align-items-center text-center">
                            <div class="d-flex justify-content-center">
                                                    <span class="text-lg-10 pr-3 opacity-5 pb-2"><i
                                                            class="fi fi-sr-usd-circle"></i></span>
                                <h3 class="text-white font-weight-semi-bold">
                                    ${newest.amount}
                                </h3>
                            </div>

                        </div>
                    </div>
                    <div class="d-flex justify-content-end">
                        <% Integer status = newest.getStatus();
                            if (status.equals(com.sonnguyen.individual.nhs.constant.LoanStatus.PENDING.value)) out.print(" <div class='rounded text-ssm-2 bg-blue-100 text-blue-700 border-blue-700 px - 1'>Pending</div > ");
                            else if (status.equals(com.sonnguyen.individual.nhs.constant.LoanStatus.APPROVED.value)) out.print(" <div class=\"rounded text-ssm-2 bg-green-100 text-green-700 border-blue-700 px - 1\">Approved</div>");
                            if (status.equals(com.sonnguyen.individual.nhs.constant.LoanStatus.REJECTED.value)) out.print(" <div class=\"rounded text - ssm - 2bg - red - 100text - red - 700border - red - 700px - 1\">Rejected</div>");
                        %>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-body">
                    <div class="card-title d-flex align-items-center">
                        <span class="mr-1"><i class="fi fi-sr-time-past"></i></span>
                        History
                    </div>
                    <table class="table table-primary">
                        <thead>
                        <td></td>
                        <td>Create At</td>
                        <td>Amount</td>
                        <td>Term</td>
                        <td>Interest Rate</td>
                        <td>Status</td>
                        <td>Action</td>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.loans}" var="loan_">
                            <tr>
                                <td>${loan_.id}</td>
                                <td>${loan_.createAt}</td>
                                <td><fmt:formatNumber value="${loan_.amount}" type="currency" /> </td>
                                <td>${loan_.term}</td>
                                <td>${loan_.interestRate}</td>
                                <td>${loan_.status}</td>
                                <td>
                                    <c:if test="${loan_.status==1}">
                                        <a href="<%=request.getContextPath()%>/app/loan/detail?id=${loan_.id}" class="link-1">Details</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>

    </div>

    <%-- loan management tool--%>
    <div class="col-lg-3">
        <div class="mx-0 row">
            <a href="${pageContext.request.contextPath}/app/loan/create" class="col-ssm-6">
                <div class="card react-widget gradient-3">
                    <div class="gutter-ssm-2">
                        <div class="col">
                            <h4 class="text-white text-md-8">Create a Loan</h4>
                            <span class="float-right text-ssm-11 opacity-5 "><i
                                    class="fi fi-sr-money-bill-transfer"></i></span>
                        </div>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>