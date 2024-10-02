<%@ taglib prefix="title" uri="http://tiles.apache.org/tags-tiles" %>
<%@include file="/taglib/taglib.jsp"%>


<tiles:definition name="cur.page" extends="page">
    <tiles:putAttribute name="title" value="Transfer"/>
    <title:putAttribute name="css" value="transfer_page_css"/>
    <tiles:putAttribute name="body">
        <tiles:definition extends="main-wrapper">
            <tiles:putAttribute name="content" value="/page/user/EnterOTP/content.jsp">
            </tiles:putAttribute>
        </tiles:definition>
    </tiles:putAttribute>
    <tiles:putAttribute name="script"  value="transfer_page_script"/>
    <tiles:putAttribute name="inline-script" value="/page/user/EnterOTP/script.jsp"/>
</tiles:definition>


<tiles:insertDefinition name="cur.page"/>