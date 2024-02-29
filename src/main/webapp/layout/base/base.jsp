<%@include file="/taglib/taglib.jsp"%>
<!DOCTYPE html>
<html class="h-100" lang="en">

<head>
    <%@include file="/taglib/header.jsp"%>
    <title>404 - Not found</title>
</head>

<body class="h-100">
<!--*******************
    Preloader start
********************-->
<div id="preloader">
    <div class="loader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10" />
        </svg>
    </div>
</div>
<!--*******************
    Preloader end
********************-->
<tiles:insertAttribute name="content"/>

<%@include file="/taglib/basescript.jsp"%>
</body>
</html>






