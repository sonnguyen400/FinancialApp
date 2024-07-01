<%@ page import="com.sonnguyen.individual.nhs.Model.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collection" %>
<%!
    List<Account> accounts;
%>
<%
    accounts= (List<Account>)request.getAttribute("accounts");
%>
<div class="row">
    <div class="col-lg-6">
        <div class="card gradient-10 text-white">
            <div class="card-body">
                <div class="col mb-3">
                    <p class="mb-1 opacity-5">Membership</p>
                    <h5 class="text-white font-weight-semi-bold">Standard</h5>
                </div>
                <div class="row align-items-center">
                    <div class="col align-items-center text-center">
                        <div class="d-flex justify-content-center">
                            <span class="text-lg-10 pr-3 opacity-5 pb-2"><i class="fi fi-sr-usd-circle"></i></span>
                            <h3 class="text-white text-xl-10">Credit Limit</h3>
                        </div>
                        <h3 class="text-white font-weight-semi-bold">142.363.351</h3>
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
                        <p class="text-sm-6 mb-1 opacity-7">Disbursement Account  </p>
                        <h4 class="text-white" id="disbursement_account_ref"> <%=accounts.get(0).getAccountNumber()%></h4>
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
                <form  method="post" action="${pageContext.request.contextPath}/app/loan/create">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="DisbursementAccount">Disbursement Account</label>
                                <select name="disbursementAccountNumber" id="DisbursementAccount" class="form-control input-default">
                                    <%
                                        for(Account account : accounts){
                                            out.print("<option value='"+account.getAccountNumber()+"'>"+account.getAccountNumber()+"</option>");
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="term">Term</label>
                                <select class="form-control" name="term" >
                                    <option value="6">6 Months</option>
                                    <option value="12">12 Months</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="amount">Amount</label>
                                <input type="number" name="amount" min="100000" value="0" class="form-control">
                                <input name="LoanCreate" value="" type="hidden">
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