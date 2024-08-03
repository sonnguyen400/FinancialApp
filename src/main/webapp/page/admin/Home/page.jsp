<tiles:insertDefinition name="admin-loan-manage.page">
    <tiles:definition name="admin-loan-manage.page" extends="page">
        <tiles:putAttribute name="title" value="LoanManage"/>
        <tiles:putAttribute name="body">
            <tiles:definition extends="main-wrapper">
                <tiles:putAttribute name="content" >
                    <tiles:definition extends="user.default-layout">
                        <tiles:putAttribute name="content" value="/page/admin/Home/content.jsp"/>
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
        <tiles:putAttribute name="inline-script" value="/page/admin/LoanManage/script.jsp"/>
    </tiles:definition>
</tiles:insertDefinition>