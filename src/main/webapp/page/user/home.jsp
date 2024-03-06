<%@ taglib prefix="title" uri="http://tiles.apache.org/tags-tiles" %>
<%@include file="/taglib/taglib.jsp"%>
<tiles:insertDefinition name="page">
<%--    head--%>
    <tiles:putAttribute name="head">
        <tiles:insertDefinition name="head">
            <tiles:insertAttribute name="title" value="Home"/>
        </tiles:insertDefinition>
    </tiles:putAttribute>
<%--    body--%>

    <tiles:putAttribute name="body">
        <tiles:insertDefinition name="body">
<%--          Body content--%>
            <tiles:putAttribute name="content">
                <tiles:putAttribute name="header" value="/tiles/user/header.jsp"/>
                <tiles:putAttribute name="navheader" value="/tiles/user/navheader.jsp"/>
                <tiles:putAttribute name="sidebar" value="/tiles/user/sidebar.jsp"/>
                <tiles:putAttribute name="content" value="/tiles/user/home-content.jsp"/>
            </tiles:putAttribute>
<%--         Javascript--%>
            <tiles:putAttribute name="script">
                <tiles:insertDefinition name="script">
                    <tiles:putListAttribute name="scripts">
                        <title:addListAttribute>
                            <title:insertAttribute value="/taglib/taglib.jsp"/>
                        </title:addListAttribute>
                    </tiles:putListAttribute>
                </tiles:insertDefinition>
            </tiles:putAttribute>
        </tiles:insertDefinition>
    </tiles:putAttribute>

</tiles:insertDefinition>