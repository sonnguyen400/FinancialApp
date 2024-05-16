<%@ page import="com.sonnguyen.individual.nhs.Model.Loan" %>
<%
    Loan loan= (Loan) request.getAttribute("loan");
%>
<div class="row justify-content-center">
    <div class="col-ssm col-sm-6 col-xl-3 align-self-center">
        <div class="invoice shadow-lg">
            <div class="invoice-content">
                <div class="p-3 col text-center text-lg text-primary p-5 border-bottom-dashed">
                    <i class="fi fi-ts-check-circle"></i>
                    <h4 class="text-info opacity-5">Successful</h4>
                    <h2 class="text-primary pt-3"><%=loan.getAmount()%></h2>
                </div>
                <div class="pt-5 pb-1 px-3">
                    <table class="invoice-infor table">
                        <tr>
                            <td>Status</td>
                            <td>
                                <span><%=loan.getStatus()%></span><br>
                            </td>
                        </tr>
                        <tr>
                            <td>Interest rate</td>
                            <td><%=loan.getInterestRate()%></td>
                        </tr>
                        <tr>
                            <td>Loan details</td>
                            <td>This loan will be forward to employee. You will receive notification when your loan 's approved</td>
                        </tr>
                    </table>
                </div>
                <div class="px-5 py-4 ">
                    <div class="row">
                        <a href="<%=request.getContextPath()%>/app/home" class="btn btn-outline-primary col mr-3"><i class="fi fi-rr-print mr-2"></i>Back To Home</a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>