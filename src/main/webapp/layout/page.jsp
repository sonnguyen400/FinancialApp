
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="ex" uri="/WEB-INF/custom.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/taglib/taglib.jsp"%>
<%
    if(session.getAttribute("lang")==null){
        session.setAttribute("lang",request.getLocalName());
    }
%>
<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title><tiles:insertAttribute name="title"/></title>
    <tiles:insertAttribute name="css"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/> "/>
    <link rel="stylesheet" href="<c:url value='/resources/plugins/sweetalert/css/sweetalert.css'/>"/>
</head>
<body>
<div id="preloader">
    <div class="loader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10"/>
        </svg>
    </div>
</div>
<jsp:useBean id="alert" scope="request" class="com.sonnguyen.individual.nhs.dto.Alert"/>
<c:if test="${alert!=null}">
    <ex:alert type="${alert.type.name()}" link="${alert.link}" href="${alert.href}">${alert.message}</ex:alert>
</c:if>
<tiles:insertAttribute name="body"/>
<script src='<c:url value="/resources/plugins/common/common.min.js"/>'></script>
<script src='<c:url value="/resources/js/custom.min.js"/>'></script>
<script src='<c:url value="/resources/js/settings.js"/> '></script>
<script src='<c:url value="/resources/js/gleek.js"/>'></script>
<script src='<c:url value="/resources/js/styleSwitcher.js"/>'></script>
<script src='<c:url value="/resources/js/base.js"/>'></script>
<script src='<c:url value="/resources/plugins/sweetalert/js/sweetalert.min.js"/>'></script>
<%--<tiles:insertAttribute name="script"/>--%>
<tiles:insertAttribute name="inline-script"/>
</body>
</html>
