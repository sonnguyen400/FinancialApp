<%@ page import="com.sonnguyen.individual.nhs.Service.AccountService" %>
<%@ page import="static com.sonnguyen.individual.nhs.Utils.Constants.CONFIRM_PIN" %>
<%@ page import="static com.sonnguyen.individual.nhs.Utils.Constants.PIN" %>
<%@ page import="java.io.IOException" %>
<%@ page import="static com.sonnguyen.individual.nhs.Utils.Constants.*" %>
<%@ page import="com.sonnguyen.individual.nhs.Model.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="static com.sonnguyen.individual.nhs.Constant.RequestFlags.CREATE_TRANSFER" %>
<%!String accountNumber;
String amount;
String message;
Object receiver_name;
List<Account> accounts;
%>
<%
    accountNumber=request.getParameter("account_number");
    amount=request.getParameter("amount");
    message=request.getParameter("message");
    receiver_name= request.getAttribute("receiver_name");
    accounts= (List<Account>) request.getAttribute("accounts");
%>

<div class="row">
    <div class="col">
        <form id="form1" method="POST" class="row" action="${pageContext.request.contextPath}/app/transfer">
            <div class="part card col">
                <div class="card-body">
                    <div class="form-group col">
                        <select name="accountId" data-rule="none" class="form-control input-default">
                            <%
                                for(Account account : accounts){
                                    out.print("<option value='"+account.getId()+"'>"+account.getAccountNumber()+"</option>");
                                }
                            %>
                        </select>
                    </div>
                    <div class="form-group col">
                        <label for="account_number" class="col-form-label">
                            <span>Receiver account number</span>
                            <span class="text-danger">*</span>
                        </label>
                        <input type="text" data-rule="none" name="account_number" class="form-control input-default"
                               id="receiver_account_number"
                               value="<%=accountNumber==null?"":accountNumber%>">
                    </div>
                    <div class="form-group col">
                        <label for="amount" class="col-form-label">
                            <span>Amount</span>
                            <span class="text-danger">*</span>
                        </label>
                        <input type="number" data-rule="required" min=0 name="amount" class="form-control input-default"
                               value="<%=amount==null?"":amount%>">
                    </div>
                    <div class="form-group col">
                        <label for="message" class="col-form-label">
                            <span>Message</span>
                            <span class="text-danger">*</span>
                        </label>
                        <input type="text" data-rule="none" name="message" class="form-control input-default"
                               value="<%=message==null?"":message%>">
                    </div>
                </div>

            </div>
            <!-- Transfer infor -->
            <div class=" part col">
                <div class="card">
                    <div class="card-body">
                        <div class="col">
                            <div id="current-balance col-form-label">Current Balance</div>
                            <h2 class="text-xxl blue mt-2">125.000.000</h2>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <form id="form2">
                            <div class="form-group col">
                                <label id="current-balance col-form-label">Receiver Information</label>
                                <input data-rule="required" style="font-size: 18px;color: gray;text-transform: uppercase;letter-spacing: 2px" class="input-default form-control"   disabled id="receiver_name">
                            </div>
                        </form>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary" >Continue</button>
            </div>
        </form>
    </div>
</div>
