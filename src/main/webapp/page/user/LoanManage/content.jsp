<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.sonnguyen.individual.nhs.Model.Loan" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sonnguyen.individual.nhs.Constant.LoanStatus" %>
<div class="row">
    <%!
        List<Loan> loan;
    %>
    <%
        loan=(List<Loan>) request.getAttribute("loans");
    %>
   <div class="col-lg-9">
       <% if (loan.size()>0) { %>

       <div class="card gradient-10 text-white">
           <div class="card-body">
               <div class="col mb-3">
                   <p class="mb-1 opacity-5"><%=loan.get(0).getCreateAt()%></p>
                   <h5 class="text-white font-weight-semi-bold"><%=loan.get(0).getTerm()%> Month</h5>
                   <h5 class="text-white font-weight-semi-bold"><%=loan.get(0).getDisbursementAccountNumber()%></h5>
               </div>
               <div class="row align-items-center">
                   <div class="col align-items-center text-center">
                       <div class="d-flex justify-content-center">
                           <span class="text-lg-10 pr-3 opacity-5 pb-2"><i class="fi fi-sr-usd-circle"></i></span>
                           <h3 class="text-white font-weight-semi-bold"><%=loan.get(0).getAmount()%></h3>
                       </div>

                   </div>
               </div>
               <div class="d-flex justify-content-end">
                   <%
                       String status=loan.get(0).getStatus();
                       if(status.equals(LoanStatus.PENDING.value)) out.print(" <div class=\"rounded text-ssm-2 bg-blue-100 text-blue-700 border-blue-700 px-1\">Pending</div>");
                       else if(status.equals(LoanStatus.APPROVED.value)) out.print(" <div class=\"rounded text-ssm-2 bg-green-100 text-green-700 border-blue-700 px-1\">Approved</div>");
                       if(status.equals(LoanStatus.REJECTED.value)) out.print(" <div class=\"rounded text-ssm-2 bg-red-100 text-red-700 border-red-700 px-1\">Rejected</div>");
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
                   </thead>
                   <tbody>
                   <%
                       for(Loan loan_:loan){
                           out.print("<tr>" +
                                   "<td>"+loan_.getId()+"</td>" +
                                   "<td>"+loan_.getCreateAt()+"</td>" +
                                   "<td>"+loan_.getAmount()+"</td>" +
                                   "<td>"+loan_.getTerm()+"</td>"+
                                   "<td>"+loan_.getInterestRate()+"</td>"+
                                   "<td>"+loan_.getStatus()+"</td>"+
                                   "</tr>");
                       }
                   %>
                   </tbody>
               </table>
           </div>
       </div>
       <% } else { %>

       <% } %>

   </div>

<%--    loan management tool--%>
    <div class="col-lg-3">
        <div class="mx-0 row">
            <a href="${pageContext.request.contextPath}/app/saving" class="col-ssm-6">
                <div class="card react-widget gradient-3">
                    <div class="gutter-ssm-2">
                        <div class="col">
                            <h4 class="text-white text-md-8">Create a saving</h4>
                            <span class="float-right text-ssm-11 opacity-5 "><i
                                    class="fi fi-sr-money-bill-transfer"></i></span>
                        </div>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>