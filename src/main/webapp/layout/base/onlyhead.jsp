<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/taglib/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Quixlab - Bootstrap Admin Dashboard Template by Themefisher.com</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
    <!-- Custom Stylesheet -->
    <link rel="stylesheet" href='<c:url value="/resources/plugins/highlightjs/styles/darkula.css">'>
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet">

</head>

<body>

<!--*******************
    Preloader start
********************-->
<div id="preloader">
    <div class="loader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10"/>
        </svg>
    </div>
</div>
<!--**********************************
    Main wrapper start
***********************************-->
<div id="main-wrapper">

    <!--**********************************
        Nav header start
    ***********************************-->
    <tiles:insertAttribute name="navheader"/>
    <!--**********************************
        Nav header end
    ***********************************-->

    <!--**********************************
        Header start
    ***********************************-->
    <tiles:insertAttribute name="header"/>
    <!--**********************************
        Header end ti-comment-alt
    ***********************************-->

    <!--**********************************
        Content body start
    ***********************************-->
    <div class="content-body">

            <%--        <div class="row page-titles mx-0">--%>
            <%--            <div class="col p-md-0">--%>
            <%--                <ol class="breadcrumb">--%>
            <%--                    <li class="breadcrumb-item"><a href="javascript:void(0)">Dashboard</a></li>--%>
            <%--                    <li class="breadcrumb-item active"><a href="javascript:void(0)">Home</a></li>--%>
            <%--                </ol>--%>
            <%--            </div>--%>
            <%--        </div>--%>
        <!-- row -->

        <div class="container-fluid">
            <tiles:insertAttribute name="content"/>
        </div>
        <!-- #/ container -->
    </div>
    <!--**********************************
        Content body end
    ***********************************-->


    <!--**********************************
        Footer start
    ***********************************-->
    <!--**********************************
        Footer end
    ***********************************-->
</div>

<!--**********************************
    Scripts
***********************************-->
<%@include file="/taglib/basescript.jsp" %>
<script src='<c:url value="/resources/plugins/highlightjs/highlight.pack.min.js"/> '></script>
<script>hljs.initHighlightingOnLoad();</script>
<script>
    (function ($) {
        "use strict"
        new quixSettings({
            version: "light", //2 options "light" and "dark"
            layout: "horizontal", //2 options, "vertical" and "horizontal"
            navheaderBg: "color_1", //have 10 options, "color_1" to "color_10"
            headerBg: "color_1", //have 10 options, "color_1" to "color_10"
            sidebarStyle: "vertical", //defines how sidebar should look like, options are: "full", "compact", "mini" and "overlay". If layout is "horizontal", sidebarStyle won't take "overlay" argument anymore, this will turn into "full" automatically!
            sidebarBg: "color_1", //have 10 options, "color_1" to "color_10"
            sidebarPosition: "static", //have two options, "static" and "fixed"
            headerPosition: "fixed", //have two options, "static" and "fixed"
            containerLayout: "wide",  //"boxed" and  "wide". If layout "vertical" and containerLayout "boxed", sidebarStyle will automatically turn into "overlay".
            direction: "ltr" //"ltr" = Left to Right; "rtl" = Right to Left
        });
    })(jQuery);
</script>

</body>

</html>