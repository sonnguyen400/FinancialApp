<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="static com.sonnguyen.individual.nhs.utils.RequestUtils.ERROR_MESSAGE" %>
<%@include file="/taglib/taglib.jsp"%>
<%--<fmt:setLocale value="${pageContext.request.locale.toString()}"/>--%>
<fmt:setLocale value="vi"/>
<fmt:setBundle basename="lang"/>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8"/>
    <link rel='stylesheet'
          href='<c:url value="https://cdn-uicons.flaticon.com/2.1.0/uicons-solid-rounded/css/uicons-solid-rounded.css"/>'>
    <%@include file="/taglib/header.jsp" %>
</head>

<body>
<%
    String string=(String)request.getAttribute(ERROR_MESSAGE);
    if(string!=null)
        out.println("<div class=\"alert alert-danger\">"+string+"!</div>");
%>
<div class="d-flex justify-content-center align-items-center" style="height: 100vh">
    <div class="card mw-570 w-100">
        <div class="card-body mw-570 w-100">
            <h4 class="text-center"><fmt:message key="login"/></h4>
            <h2 class="text-center">Harmony U</h2>
            <!-- Form 1 -->
            <form action="${pageContext.request.contextPath}/login" style="display: block;" method="post" id="form1">
                <div class="part">
                    <div class=" form-group">
                        <label class="pb-2" for="firstname"><fmt:message key="username"/></label>
                        <input id="firstname" name="username" class="form-control" data-rule="required" type="text"
                               placeholder="<fmt:message key="password" />" />
                        <span class="invalid-feedback animated fadeInDown"></span>
                    </div>
                    <div class=" form-group">
                        <label class="pb-2" ><fmt:message key="password" /></label>
                        <input  name="password" class="form-control" data-rule="required" type="password"
                               placeholder="<fmt:message key="password" />" />
                        <span class="invalid-feedback animated fadeInDown"></span>
                    </div>
                    <div class=""><fmt:message key="page.login.footer.message"/> <a class="link" href="${pageContext.request.contextPath}/register"><fmt:message key="register"/></a></div>
                    <div class="d-flex justify-content-end">
                        <button class="btn btn-primary mx-2" id="submitBtn" type="submit" ><fmt:message key="login" /></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/taglib/basescript.jsp" %>
<%@include file="/tiles/base/plugin/Form.jsp"%>
<script src='<c:url value="/resources/js/FormValidator.js"/>'></script>
<script>
    $(function(){
        stepFormValidate($("#form1"))
    })
</script>
</body>

</html>