<!-- <%--
  Created by IntelliJ IDEA.
  User: Acer Swift 3
  Date: 2/24/2024
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%> -->
<%@include file="/taglib/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Register</title>
    <link rel='stylesheet'
          href='<c:url value="https://cdn-uicons.flaticon.com/2.1.0/uicons-solid-rounded/css/uicons-solid-rounded.css"/>'>
    <%@include file="/taglib/header.jsp" %>
</head>

<body>
<div class="d-flex justify-content-center align-items-center" style="height: 100vh">
    <div class="card mw-570 w-100">
        <div class="card-body mw-570 w-100">
            <h4 class="text-center">Register</h4>
            <h2 class="text-center">Harmony U</h2>
            <p class="text-black-50 text-sm-center ">Register and receive all your desired treats</p>
            <!-- Form 1 -->
            <form action="" style="display: block;" method="post" id="form1">
                <div class=" form-group">
                    <label class="pb-2" for="firstname">Username</label>
                    <input id="firstname" name="firstname" class="form-control" data-rule="required" type="text"
                           placeholder="Username" />
                    <span class="invalid-feedback animated fadeInDown"></span>
                </div>
                <div class=" form-group">
                    <label class="pb-2" for="lastname">Password</label>
                    <input id="lastname" name="lastname" class="form-control" data-rule="required" type="password"
                           placeholder="Password" />
                    <span class="invalid-feedback animated fadeInDown"></span>
                </div>
                <div class="d-flex justify-content-end">
                    <button class="btn btn-primary mx-2" type="submit" >Login</button>
                </div>
            </form>
        </div>
    </div>

</div>
<%@include file="/taglib/basescript.jsp" %>
<script type="module">
    import Form from '<c:url value="/resources/js/FormValidator.js"/>'
    $(function () {
        let form1 = Form("#form1")
    })
</script>
</body>

</html>