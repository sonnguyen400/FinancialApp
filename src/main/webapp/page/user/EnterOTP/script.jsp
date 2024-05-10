<%@ page import="static com.sonnguyen.individual.nhs.Utils.RequestUtils.ERROR_MESSAGE" %>
<script>
    <%
          if(request.getAttribute(ERROR_MESSAGE)!=null){
              out.print("sweetAlert(\"Oops...\", \" "+ request.getAttribute(ERROR_MESSAGE)+ " \", \"error\");");
          }
    %>
</script>
