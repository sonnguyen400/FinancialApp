<%@include file="/taglib/taglib.jsp"%>
<tiles:insertDefinition name="page">
    <tiles:putAttribute name="header" value="/tiles/admin/header.jsp"/>
    <tiles:putAttribute name="navheader" value="/tiles/admin/navheader.jsp"/>
    <tiles:putAttribute name="sidebar" value="/tiles/admin/sidebar.jsp"/>
    <tiles:putAttribute name="content" value="/tiles/admin/main.jsp"/>
</tiles:insertDefinition>