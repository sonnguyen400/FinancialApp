<%@ page import="com.sonnguyen.individual.nhs.Utils.SessionUtils" %>
<%@ page import="com.sonnguyen.individual.nhs.Model.Login" %>
<%@include file="/taglib/taglib.jsp"%>
<%
    if(SessionUtils.getPrincipal(request)!=null){
        response.sendRedirect(request.getContextPath()+"/app");
    }else{
        response.sendRedirect(request.getContextPath()+"/login");
    }
%>