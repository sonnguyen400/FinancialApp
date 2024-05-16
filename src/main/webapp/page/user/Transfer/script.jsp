<%@ page import="static com.sonnguyen.individual.nhs.Utils.RequestUtils.ERROR_MESSAGE" %>
<%@ page import="static com.sonnguyen.individual.nhs.Utils.Constants.EXACT_PIN" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="<c:url value="/resources/js/FormValidator.js"/>"></script>
<script type="module">

    (function ($) {
        "use strict"
        new quixSettings({
            version: "light", //2 options "light" and "dark"
            layout: "horizontal", //2 options, "vertical" and "horizontal"
            navheaderBg: "color_1", //have 10 options, "color_1" to "color_10"
            headerBg: "color_1", //have 10 options, "color_1" to "color_10"
            sidebarStyle: "vertical", //defines how sidebar should look like, options are: "full", "compact", "mini" and "overlay". If layout is "horizontal", sidebarStyle won't take "overlay" argument anymore, this will turn into "full" automatically!
            sidebarBg: "color_1", //have 10 options, "color_1" to "color_10"
            sidebarPosition: "static", //have two options, "static" and "fixed"
            headerPosition: "fixed", //have two options, "static" and "fixed"
            containerLayout: "wide",  //"boxed" and  "wide". If layout "vertical" and containerLayout "boxed", sidebarStyle will automatically turn into "overlay".
            direction: "ltr" //"ltr" = Left to Right; "rtl" = Right to Left
        });
        $("#receiver_account_number").on("blur", function(e){
            let data={
                accountNumber:this.value
            }
            $.post("<%=request.getContextPath()%>/app/ajax/accountNumber",data).done(data=>{
                let user=JSON.parse(data);
                $("#receiver_name").val(user.firstname+" "+user.lastname);
                return data;
            }).fail(()=>{
                sweetAlert("Invalid account number");
            })
        })
        <%
            if(request.getAttribute(ERROR_MESSAGE)!=null){
                out.print("sweetAlert(\"Oops...\", \" "+ request.getAttribute(ERROR_MESSAGE)+ " \", \"error\");");
            }
        %>
        <%
            if(request.getAttribute(EXACT_PIN)!=null){
                out.print("$(\"#enterOTPForm\").modal();");
            }
        %>
    })(jQuery);

</script>