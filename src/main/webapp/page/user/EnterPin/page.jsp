<%@ taglib prefix="title" uri="http://tiles.apache.org/tags-tiles" %>
<%@include file="/taglib/taglib.jsp"%>


<tiles:definition name="transfer_page_script" extends="script" template="/definition/javascript.jsp">
    <tiles:putListAttribute inherit="true" name="script">
        <tiles:addAttribute value="/resources/js/horizontal.js"/>
        <title:addAttribute value="/resources/plugins/toastr/js/toastr.min.js"/>
        <title:addAttribute value="/resources/plugins/sweetalert/js/sweetalert.min.js"/>
    </tiles:putListAttribute>
</tiles:definition>
<tiles:definition name="transfer_page_css" extends="css" template="/definition/css.jsp">
    <tiles:putListAttribute inherit="true" name="css">
        <tiles:addAttribute value="/resources/plugins/sweetalert/css/sweetalert.css"/>
    </tiles:putListAttribute>
</tiles:definition>
<tiles:definition name="cur.page" extends="page">
    <tiles:putAttribute name="title" value="Transfer"/>
    <title:putAttribute name="css" value="transfer_page_css"/>
    <tiles:putAttribute name="body">
        <tiles:definition extends="main-wrapper">
            <tiles:putAttribute name="content">
                <tiles:definition  extends="user.header-layout">
                    <tiles:putAttribute name="content" value="/page/user/EnterPin/content.jsp"/>
                </tiles:definition>
            </tiles:putAttribute>
        </tiles:definition>
    </tiles:putAttribute>
    <tiles:putAttribute name="script"  value="transfer_page_script"/>
    <tiles:putAttribute name="inline-script" value="/page/user/EnterPin/script.jsp"/>
</tiles:definition>


<tiles:insertDefinition name="cur.page"/>