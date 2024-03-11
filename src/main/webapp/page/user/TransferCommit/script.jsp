<%@ page import="static com.sonnguyen.individual.nhs.Utils.RequestUtils.ERROR_MESSAGE" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="module">
    import Form from '<c:url value="/resources/js/formvalidator.js"/>';
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
        var form1 = Form("#form1");
        var form2 = Form("#form2");
        var form3 = Form("#form3");
        $("#receiver_account_number").on("blur", function (e) {
            $("#preloader").fadeIn(500);
            let payload={}
            form1.formData().entries().forEach(value=>{
                Object.assign(payload,{[value[0]]:value[1]})
            })
            $("#form1submit").click();
        })


        <%
            if(request.getAttribute(ERROR_MESSAGE)!=null){
                out.print("sweetAlert(\"Oops...\", \" "+ request.getAttribute(ERROR_MESSAGE)+ " \", \"error\");");
            }
        %>
    })(jQuery);

</script>