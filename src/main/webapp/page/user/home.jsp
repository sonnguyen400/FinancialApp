<%@include file="/taglib/taglib.jsp"%>
<tiles:insertDefinition name="main.base">
    <tiles:putAttribute name="header" value="/tiles/user/header.jsp"/>
    <tiles:putAttribute name="navheader" value="/tiles/user/navheader.jsp"/>
    <tiles:putAttribute name="sidebar" value="/tiles/user/sidebar.jsp"/>
    <tiles:putAttribute name="content" value="/tiles/user/home-content.jsp"/>
</tiles:insertDefinition>