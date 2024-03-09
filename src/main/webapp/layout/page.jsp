<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
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
<tiles:insertAttribute name="body"/>
<tiles:insertAttribute name="script"/>
<script>
    <tiles:insertAttribute name="inline-script"/>
</script>
</body>
</html>
