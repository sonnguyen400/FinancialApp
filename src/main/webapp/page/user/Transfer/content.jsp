<%@ page import="com.sonnguyen.individual.nhs.Service.AccountService" %>
<%!String accountNumber;
String amount;
String message;%>
<%
    accountNumber=request.getParameter("account_number");
    amount=request.getParameter("amount");
    amount=request.getParameter("message");
%>
<div class="row">
    <div class="col">
        <div class="card">
            <div class="card-body">
                <form id="form1" method="POST" action="${pageContext.request.contextPath}/transfer">
                    <div class="form-group col">
                        <label for="amount" class="col-form-label">
                            <span>Organize</span>
                            <span class="text-danger">*</span>
                        </label>
                        <select data-rule="none" class="form-control input-default">
                            <option value="">Mb</option>
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
                    <button type="submit" class="d-none" id="form1submit"></button>
                </form>
            </div>
        </div>
    </div>
    <!-- Transfer infor -->
    <div class="col">
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
                        <input class="input-default form-control" data-rule="none" disabled id="receiver_name"
                        value="<%=request.getAttribute("receiver_name")%>">
                    </div>
                </form>
            </div>
        </div>
        <div class="bootstrap-modal">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary">Continue</button>
        </div>
    </div>
</div>
<div class="modal fade" id="EnterPIN" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Enter your PIN</h5>
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span>
                </button>
            </div>
            <form action="POST" id="form3">
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col form-group">
                                <input type="PIN" name="pin-0" maxlength="1" data-rule="none"
                                       class="p-2 text-lg form-control input-default">
                            </div>
                            <div class="col form-group">
                                <input type="PIN" name="pin-1" maxlength="1" data-rule="none"
                                       class="p-2 text-lg form-control input-default">
                            </div>
                            <div class="col form-group">
                                <input type="PIN" name="pin-2" maxlength="1" data-rule="none"
                                       class="p-2 text-lg form-control input-default">
                            </div>
                            <div class="col form-group">
                                <input type="PIN" name="pin-3" maxlength="1" data-rule="none"
                                       class="p-2 text-lg form-control input-default">
                            </div>
                            <div class="col form-group">
                                <input type="PIN" name="pin-4" maxlength="1" data-rule="none"
                                       class="p-2 text-lg form-control input-default">
                            </div>
                            <div class="col form-group">
                                <input type="PIN" name="pin-5" maxlength="1" data-rule="none"
                                       class="p-2 text-lg form-control input-default">
                            </div>
                            <input type="hidden" name="PIN_ENTERED">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <div class="bootstrap-modal">
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary">Continue</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<button type="hidden" id="triggerAlert"></button>