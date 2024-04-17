<%@ page import="com.sonnguyen.individual.nhs.Model.Account" %>
<%@ page import="com.sonnguyen.individual.nhs.Utils.SessionUtils" %>
<%@include file="/taglib/taglib.jsp"%>
<%
    if(((Account)session.getAttribute(SessionUtils.LOGIN_SESSION))!=null){
        response.sendRedirect(request.getContextPath()+"/app/home");
    }else{
        response.sendRedirect(request.getContextPath()+"/login");
    }
%>