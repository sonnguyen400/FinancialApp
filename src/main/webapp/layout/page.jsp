<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="ex" uri="/WEB-INF/custom.tld" %>
<%@include file="/taglib/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" href='<c:url value="/resources/plugins/chartist/css/chartist.min.css"/>'>
    <link rel="stylesheet"
          href='<c:url value="/resources/plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css"/>'>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title><tiles:insertAttribute name="title"/></title>
    <tiles:insertAttribute name="css"/>
    <tiles:insertAttribute name="head-props"/>
</head>
<body>
<div id="preloader">
    <div class="loader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10"/>
        </svg>
    </div>
</div>
<jsp:useBean id="message" scope="request" class="com.sonnguyen.individual.nhs.dto.Alert"/>
<c:if test="${message!=null}">
    <ex:alert type="${message.type.name()}" link="${message.link}" href="${message.href}">${message.message}</ex:alert>
</c:if>
<tiles:insertAttribute name="body"/>
<tiles:insertAttribute name="script"/>
<tiles:insertAttribute name="inline-script"/>
</body>
</html>
