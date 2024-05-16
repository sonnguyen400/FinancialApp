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
        $("select[name='term']").on("change",function(e){
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

    })
</script>