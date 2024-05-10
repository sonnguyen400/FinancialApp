<%@ page import="com.sonnguyen.individual.nhs.Utils.SessionUtils" %>
<%@ page import="com.sonnguyen.individual.nhs.Model.Login" %>
<%@include file="/taglib/taglib.jsp"%>
<%
    if(((Login)session.getAttribute(SessionUtils.LOGIN_SESSION))!=null){
        response.sendRedirect(request.getContextPath()+"/app/home");
    }else{
        response.sendRedirect(request.getContextPath()+"/login");
    }
%>