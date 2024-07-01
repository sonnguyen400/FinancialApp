<%@ taglib prefix="title" uri="http://tiles.apache.org/tags-tiles" %>
<%@include file="/taglib/taglib.jsp"%>



<tiles:insertDefinition name="user.home.page">
    <tiles:definition name="user.home.page" extends="page">
        <tiles:putAttribute name="title" value="Home"/>
        <tiles:putAttribute name="body">
            <tiles:definition extends="main-wrapper">
                <tiles:putAttribute name="content" >
                    <tiles:definition extends="user.default-layout">
                        <tiles:putAttribute name="content" value="/page/user/HomePage/content.jsp"/>
                    </tiles:definition>
                </tiles:putAttribute>
            </tiles:definition>
        </tiles:putAttribute>
        <tiles:putAttribute name="script">
            <tiles:definition extends="script">
                <tiles:putListAttribute inherit="true" name="script">
                    <tiles:addAttribute value="/resources/js/gleek.js"/>
                </tiles:putListAttribute>
            </tiles:definition>
        </tiles:putAttribute>
    </tiles:definition>
</tiles:insertDefinition>
