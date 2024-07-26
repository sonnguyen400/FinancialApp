<%@ page import="com.sonnguyen.individual.nhs.Constant.SavingType" %>
<%@ page import="static com.sonnguyen.individual.nhs.utils.RequestUtils.ERROR_MESSAGE" %>
<script>
    $(function (){
        $("input[name='amount']").on("input",e=>{
            $("#amountRef").html(e.target.value)
        });
        $("select[name='disbursement_account']").on("change",e=>{
            let value=$("select[name='disbursement_account'] option:selected")[0].value;
            $("input[name='disbursementAccountNumber']").val(value);
            $("#disbursement_account_ref").html(value);
        });
        var Termselector=$("select[name='term']");
        $("select[name='type']").on("change",function (e){
            let value=e.target.value;
            switch (value) {
                case "<%=SavingType.TERM_DEPOSIT%>":
                    Termselector.prop('disabled', false);
                    break;
                case "<%=SavingType.DEMAND_DEPOSIT%>":
                    Termselector.prop('disabled', true);
                    break;
            }
        })
        Termselector.on("change",function(e){
            let value=e.target.value;
            let interestRate=0;
            switch (value) {
                case "12":
                    interestRate=5;
                    break;
                case "6":
                    interestRate=10;
                    break;
            }
            $("input[name='interestRate']").val(interestRate);
            $("#interest_rate_ref").html(interestRate+`% per Year`);
        });
        <%
                  if(request.getAttribute(ERROR_MESSAGE)!=null){
                      out.print("sweetAlert(\"Oops...\", \" "+ request.getAttribute(ERROR_MESSAGE)+ " \", \"error\");");
                  }
        %>
    })
</script>