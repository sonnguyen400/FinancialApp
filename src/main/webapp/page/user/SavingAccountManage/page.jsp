<%@ taglib prefix="title" uri="http://tiles.apache.org/tags-tiles" %>
<%@include file="/taglib/taglib.jsp"%>




<tiles:insertDefinition name="cur.page">
    <tiles:definition name="cur.page" extends="page">
        <tiles:putAttribute name="title" value="Home"/>
        <tiles:putAttribute name="body">
            <tiles:definition extends="main-wrapper">
                <tiles:putAttribute name="content">
                    <tiles:definition name="loan-create-content" extends="user.default-layout">
                        <tiles:putAttribute name="content" value="/page/user/SavingAccountManage/content.jsp"/>
                    </tiles:definition>
                </tiles:putAttribute>
            </tiles:definition>
        </tiles:putAttribute>
    </tiles:definition>

</tiles:insertDefinition>
