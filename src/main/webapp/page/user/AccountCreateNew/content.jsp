<%@ page import="com.sonnguyen.individual.nhs.constant.AccountType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ex" uri="/WEB-INF/custom.tld"%>
<fmt:setLocale value="${sessionScope.lang}"  />
<fmt:setBundle basename="lang"/>
<div class="row justify-content-center ">
    <div class="col-sm-12 col-md-8 col-lg-6">
        <div class="card">
            <div class="card-body">
                <div class="card-title"><fmt:message key="account.new"/></div>
                <form method="post" >
                    <div class="form-group">
                        <label><fmt:message key="account.number"/> </label>
                        <input class="form-control" name="accountNumber">
                    </div>
                    <div class="form-group">
                        <label>Account Type</label>
                        <select class="form-control" name="accountType">
                            <option value="<%=AccountType.PRIMARY.value%>"><fmt:message key="account.standard"/> </option>
                            <option value="<%=AccountType.INCORPORATE.value%>"><fmt:message key="account.coop"/> </option>
                        </select>
                    </div>
                    <div class="d-flex justify-content-end">
                        <button class="btn btn-primary" type="submit"><fmt:message key="continue"/> </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

