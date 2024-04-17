<%@ page import="com.sonnguyen.individual.nhs.Model.Transfer" %>
<%
    Transfer transfer= (Transfer) request.getAttribute("transfer");
%>
<div class="row justify-content-center">
    <div class="col-ssm col-sm-6 col-xl-3 align-self-center">
        <div class="invoice shadow-lg">
            <div class="invoice-content">
                <div class="p-3 col text-center text-lg text-primary p-5 border-bottom-dashed">
                    <i class="fi fi-ts-check-circle"></i>
                    <h4 class="text-info opacity-5">Successful Transference</h4>
                    <h2 class="text-primary pt-3"><%=transfer.getTransaction().getValue()%></h2>
                </div>
                <div class="pt-5 pb-1 px-3">
                    <table class="invoice-infor table">
                        <tr>
                            <td>Destination Account</td>
                            <td>
                                <span><%=transfer.getAccountId()%></span><br>
                                <span>Harmony</span>
                            </td>
                        </tr>
                        <tr>
                            <td>Source Account</td>
                            <td><%=transfer.getTransaction().getAccountId()%></td>
                        </tr>
                        <tr>
                            <td>Invoice Date</td>
                            <td><%=transfer.getTransaction().getTransactionDate()%></td>
                        </tr>
                        <tr>
                            <td>Invoice Number</td>
                            <td>156789</td>
                        </tr>
                        <tr>
                            <td>Transfer details</td>
                            <td><%=transfer.getMessage()%></td>
                        </tr>
                    </table>
                </div>
                <div class="px-5 py-4 ">
                    <div class="row">
                        <button class="btn btn-outline-primary col mr-3"><i class="fi fi-rr-print mr-2"></i>Print</button>
                        <button class="btn btn-outline-primary col"><i class="fi fi-rr-share mr-2"></i>Share</button>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>