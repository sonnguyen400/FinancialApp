<%@ taglib prefix="title" uri="http://tiles.apache.org/tags-tiles" %>
<%@include file="/taglib/taglib.jsp"%>
<tiles:insertDefinition name="loan-create.page">
    <tiles:definition name="loan-create.page" extends="page">
        <tiles:putAttribute name="title" value="Wallet"/>
        <tiles:putAttribute name="css">
            <tiles:definition name="transfer_page_css" extends="css" template="/definition/css.jsp">
                <tiles:putListAttribute inherit="true" name="css">
                    <tiles:addAttribute value="/resources/plugins/sweetalert/css/sweetalert.css"/>
                </tiles:putListAttribute>
            </tiles:definition>
        </tiles:putAttribute>
        <tiles:putAttribute name="body">
            <tiles:definition extends="main-wrapper">
                <tiles:putAttribute name="content" >
                    <tiles:definition extends="user.default-layout">
                        <tiles:putAttribute name="content" value="/page/user/SavingAccountCreate/content.jsp"/>
                    </tiles:definition>
                </tiles:putAttribute>
            </tiles:definition>
        </tiles:putAttribute>
        <tiles:putAttribute name="script">
            <tiles:definition extends="script">
                <tiles:putListAttribute inherit="true" name="script">
                    <tiles:addAttribute value="/resources/js/gleek.js"/>
                    <title:addAttribute value="/resources/plugins/sweetalert/js/sweetalert.min.js"/>
                </tiles:putListAttribute>
            </tiles:definition>
        </tiles:putAttribute>
        <tiles:putAttribute name="inline-script" value="/page/user/SavingAccountCreate/script.jsp"/>
    </tiles:definition>
</tiles:insertDefinition>