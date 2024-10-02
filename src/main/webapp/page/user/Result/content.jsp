<%@ page import="com.sonnguyen.individual.nhs.dto.Message" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="result" scope="request" class="com.sonnguyen.individual.nhs.dto.Result"/>
<div class="row justify-content-center">
    <div class="col-sm-12 col-md-4 col-lg-6">
        <div class="card w-100">
            <div class="card-body">
                <h2 class="text-center">
                    <%
                        if(request!=null){
                            if (result.getType()==Message.Type.SUCCESS) {
                                out.println("<i class=\"text-sm-12 fi fi-sr-check-circle text-primary\"></i>");
                            }else if(result.getType()==Message.Type.WARNING) {
                                out.println("<i class=\" text-sm-12 fi fi-sr-triangle-warning text-warning\"></i>");
                            }else if(result.getType()==Message.Type.ERROR) {
                                out.println("<i class=\" text-sm-12 fi fi-sr-times-hexagon text-danger\"></i>");
                            }else {
                                out.println("<i class=\" text-sm-12 fi fi-sr-triangle-warning text-warning\"></i>");
                            }
                        }
                    %>
                </h2>
                <h4 class="text-center mt-4 mb-2">
                    ${result.message}
                </h4>
                <div class="text-center mt-4">
                    <a class="btn-primary btn" href="/">Home</a>
                </div>
                <div class="text-center mt-5">
                    <ul class="list-inline">
                        <li class="list-inline-item"><a href="javascript:void()" class="btn btn-facebook"><i class="fa fa-facebook"></i></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>