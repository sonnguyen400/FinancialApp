<%@ page import="static com.sonnguyen.individual.nhs.utils.RequestUtils.ERROR_MESSAGE" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<c:url value="/resources/js/FormValidator.js"/>"></script>
<script type="module">
    (function ($) {
        "use strict"
        $("#receiver_account_number").on("blur", function(e){
            let data={
                accountNumber:this.value
            }
            $.post("<%=request.getContextPath()%>/app/ajax/accountNumber",data).done(data=>{
                let user=JSON.parse(data);
                $("#receiver_name").val(user.firstname+" "+user.lastname);
                return data;
            }).fail(()=>{
                sweetAlert("Sorry","Invalid account number","error");
            })
        })
        <%
            if(request.getAttribute(ERROR_MESSAGE)!=null){
                out.print("sweetAlert('Oops...', ' "+request.getAttribute(ERROR_MESSAGE)+" ','error');");
            }
        %>
    })(jQuery);

</script>