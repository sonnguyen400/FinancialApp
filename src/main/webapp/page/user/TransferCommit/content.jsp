<%@ page import="com.sonnguyen.individual.nhs.Service.AccountService" %>
<%@ page import="static com.sonnguyen.individual.nhs.Utils.Constants.CONFIRM_PIN" %>
<%@ page import="static com.sonnguyen.individual.nhs.Utils.Constants.PIN" %>
<%@ page import="com.sonnguyen.individual.nhs.Model.Transfer" %>
<%!String accountNumber;
String amount;
String message;
Object receiver_name;%>
<%
    Transfer transfer= (Transfer) request.getAttribute("transfer");
    if(transfer!=null){
        out.print(transfer.getAccountId());
    }
%>
<div class="row">
    <div class="col">
        <div class="card">
            <div class="card-body">
                <form method="post">
                    <div class="form-group">
                        <label for="account_number">Enter OTP</label>
                        <input type="text" class="form-control" id="account_number" name="account_number">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>