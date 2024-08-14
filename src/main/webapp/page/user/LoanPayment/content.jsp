<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="loan" class="com.sonnguyen.individual.nhs.model.Loan"/>
<fmt:setLocale value="${sessionScope.lang}"  />
<fmt:setBundle basename="lang"/>

<%!Calendar now=Calendar.getInstance();%>
<div class="row justify-content-center">
    <div class="col-xl-4 col-ssm-1">
        <div class="bg-white shadow-sm">
            <div class="bg-primary p-4">
                <h4 class="font-weight-semi-bold text-white text-uppercase">Monthly loan payment for <%=now.get(Calendar.MONTH)+"/"+now.get(Calendar.YEAR)%></h4>
                <p class="text-ssm-4">

                </p>
            </div>
            <div class="card-body">
                <form method="post">
                    <input type="hidden" value="${requestScope.id}" name="id"/>
                    <div class="form-group">
                        <label class="label">From</label>
                        <select name="account_id" type="text" class="form-control">
                            <c:forEach items="${requestScope.accounts}" var="account">
                                <option value="${account.id}">${account.accountNumber}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="label">Amount</label>
                        <input name="amount" type="hidden" value="${requestScope.amount}" class="form-control disabled">
                        <div class="form-control disabled d-flex align-items-center font-weight-semi-bold color-facebook text-xl-10 "><fmt:formatNumber currencyCode="," value="${requestScope.amount.doubleValue()}" /></div>
                    </div>
                    <div class="form-group">
                        <label class="label">Message</label>
                        <input value="<%="Payment"+now.get(Calendar.MONTH)+"/"+now.get(Calendar.YEAR)+" for loan "+request.getParameter("id")%>" disabled name="description" type="text" class="form-control disabled">
                    </div>
                    <div class="d-flex justify-content-end">
                        <button class="btn btn-primary bg-primary">Continue</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
