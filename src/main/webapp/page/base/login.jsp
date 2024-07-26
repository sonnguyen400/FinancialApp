<%@ page import="static com.sonnguyen.individual.nhs.utils.RequestUtils.ERROR_MESSAGE" %>
<%@include file="/taglib/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Login</title>
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
            <h4 class="text-center">Register</h4>
            <h2 class="text-center">Harmony U</h2>
            <p class="text-black-50 text-sm-center ">Register and receive all your desired treats</p>
            <!-- Form 1 -->
            <form action="${pageContext.request.contextPath}/login" style="display: block;" method="post" id="form1">
                <div class="part">
                    <div class=" form-group">
                        <label class="pb-2" for="firstname">Username</label>
                        <input id="firstname" name="username" class="form-control" data-rule="required" type="text"
                               placeholder="Username" />
                        <span class="invalid-feedback animated fadeInDown"></span>
                    </div>
                    <div class=" form-group">
                        <label class="pb-2" for="lastname">Password</label>
                        <input id="lastname" name="password" class="form-control" data-rule="required" type="password"
                               placeholder="Password" />
                        <span class="invalid-feedback animated fadeInDown"></span>
                    </div>
                    <div class="">Don't have an account? <a class="link" href="${pageContext.request.contextPath}/register">Register</a></div>
                    <div class="d-flex justify-content-end">
                        <button class="btn btn-primary mx-2" id="submitBtn" type="submit" >Login</button>
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