<%@ taglib prefix="title" uri="http://tiles.apache.org/tags-tiles" %>
<%@include file="/taglib/taglib.jsp"%>
<tiles:insertDefinition name="page">
    <tiles:definition name="page" extends="page">
        <tiles:putAttribute name="title" value="Wallet"/>
        <tiles:putAttribute name="body">
            <tiles:definition extends="main-wrapper">
                <tiles:putAttribute name="content" >
                    <tiles:definition extends="user.default-layout">
                        <tiles:putAttribute name="content" value="/page/user/AccountDetail/content.jsp"/>
                    </tiles:definition>
                </tiles:putAttribute>
            </tiles:definition>
        </tiles:putAttribute>
        <tiles:putAttribute name="inline-script" value="/page/user/SavingAccountCreate/script.jsp"/>
    </tiles:definition>
</tiles:insertDefinition>