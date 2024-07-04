<%@ page import="java.util.Objects" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row justify-content-center">
    <div class="d-flex flex-column justify-content-center">
        <div class="card">
            <div class="card-body">
                <h2 class="text-center">
                    <%
                        if(request.getAttribute("status")!=null){
                            if (request.getAttribute("status").equals(1)) {
                                out.println("<i class=\" text-sm-12 fi fi-sr-check-circle text-primary\"></i>");
                            }else if(request.getAttribute("status").equals(2)) {
                                out.println("<i class=\" text-sm-12 fi fi-sr-triangle-warning text-warning\"></i>");
                            }else if(request.getAttribute("status").equals(3)) {
                                out.println("<i class=\" text-sm-12 fi fi-sr-times-hexagon text-danger\"></i>");
                            }else {
                                out.println("<i class=\" text-sm-12 fi fi-sr-triangle-warning text-warning\"></i>");
                            }
                        }
                    %>
                </h2>
                <h4 class="text-center mt-4 mb-2">
                    <%=request.getAttribute("message")%>
                </h4>
                <div class="text-center mt-4">
                    <a class="btn-primary btn" href="/">Home</a>
                </div>
                <div class="text-center mt-5">
                    <p>Copyright © Designed by <a href="https://themeforest.net/user/digitalheaps">Digitalheaps</a>, Developed by <a href="https://themeforest.net/user/quixlab">Quixlab</a> 2018</p>
                    <ul class="list-inline">
                        <li class="list-inline-item"><a href="javascript:void()" class="btn btn-facebook"><i class="fa fa-facebook"></i></a>
                        </li>
                        <li class="list-inline-item"><a href="javascript:void()" class="btn btn-twitter"><i class="fa fa-twitter"></i></a>
                        </li>
                        <li class="list-inline-item"><a href="javascript:void()" class="btn btn-linkedin"><i class="fa fa-linkedin"></i></a>
                        </li>
                        <li class="list-inline-item"><a href="javascript:void()" class="btn btn-google-plus"><i class="fa fa-google-plus"></i></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>