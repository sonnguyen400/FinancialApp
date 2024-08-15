<%@ taglib prefix="title" uri="http://tiles.apache.org/tags-tiles" %>
<%@include file="/taglib/taglib.jsp"%>
<tiles:definition name="cur.page" extends="page">
    <tiles:putAttribute name="title" value="HarmonyU"/>
    <tiles:putAttribute name="body">
        <tiles:definition extends="main-wrapper">
            <tiles:putAttribute name="content" >
                <tiles:definition extends="user.default-layout">
                    <tiles:putAttribute name="content" value="/page/user/SavingAccountCreate/content.jsp"/>
                </tiles:definition>
            </tiles:putAttribute>
        </tiles:definition>
    </tiles:putAttribute>
</tiles:definition>
<tiles:insertDefinition name="cur.page">

</tiles:insertDefinition>