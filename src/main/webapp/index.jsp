<%@ page import="com.sonnguyen.individual.nhs.utils.SessionUtils" %>
<%@ page import="com.sonnguyen.individual.nhs.model.Login" %>
<%@include file="/taglib/taglib.jsp"%>
<%
    if(SessionUtils.getPrincipal(request)!=null){
        response.sendRedirect(request.getContextPath()+"/app");
    }else{
        response.sendRedirect(request.getContextPath()+"/login");
    }
%>