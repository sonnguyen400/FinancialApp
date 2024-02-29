<%--
  Created by IntelliJ IDEA.
  User: Acer Swift 3
  Date: 2/24/2024
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/taglib/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="theme-name" content="quixlab" />
    <title>Quixlab - Bootstrap Admin Dashboard Template by Themefisher.com</title>
    <!-- Pignose Calender -->
    <link href='<c:url value="/resources/plugins/pg-calendar/css/pignose.calendar.min.css"/>' rel="stylesheet">
    <!-- Chartist -->
    <link rel="stylesheet" href='<c:url value="/resources/plugins/chartist/css/chartist.min.css"/>'>
    <link rel="stylesheet" href='<c:url value="/resources/plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css"/>'>
    <!-- Custom Stylesheet -->
    <%@include file="/taglib/header.jsp"%>

</head>

<body>

<!--*******************
    Preloader start
********************-->
<div id="preloader">
    <div class="loader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10"></circle>
        </svg>
    </div>
</div>
<!--*******************
    Preloader end
********************-->


<!--**********************************
    Main wrapper start
***********************************-->
<div id="main-wrapper" data-header-position="fixed">

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
        Sidebar start
    ***********************************-->
    <tiles:insertAttribute name="sidebar"/>
    <!--**********************************
        Sidebar end
    ***********************************-->

    <!--**********************************
        Content body start
    ***********************************-->

    <div class="content-body">
        <div class="container-fluid mt-3">
            <tiles:insertAttribute name="content"/>
        </div>
    </div>

    <!--**********************************
        Content body end
    ***********************************-->


    <!--**********************************
        Footer start
    ***********************************-->
    <div class="footer">
        <div class="copyright">
            <%--            <p>Copyright &copy; Designed & Developed by <a href="https://themeforest.net/user/quixlab">Quixlab</a> 2018</p>--%>
        </div>
    </div>
    <!--**********************************
        Footer end
    ***********************************-->
</div>
<!--**********************************
    Main wrapper end
***********************************-->

<!--**********************************
    Scripts
***********************************-->
<%@include file="/taglib/basescript.jsp"%>

<!-- Chartjs -->
<script src='<c:url value="/resources/plugins/chart.js/Chart.bundle.min.js"/> '></script>
<!-- Circle progress -->
<script src='<c:url value="/resources/plugins/circle-progress/circle-progress.min.js"/> '></script>
<!-- Datamap -->
<script src='<c:url value="/resources/plugins/d3v3/index.js"/> '></script>
<script src='<c:url value="/resources/plugins/topojson/topojson.min.js"/>'></script>
<script src='<c:url value="/resources/plugins/datamaps/datamaps.world.min.js"/>'></script>
<!-- Morrisjs -->
<script src='<c:url value="/resources/plugins/raphael/raphael.min.js"/>'></script>
<script src='<c:url value="/resources/plugins/morris/morris.min.js"/> '></script>
<!-- Pignose Calender -->
<script src='<c:url value="/resources/plugins/moment/moment.min.js"/>'></script>
<script src='<c:url value="/resources/plugins/pg-calendar/js/pignose.calendar.min.js"/>'></script>
<!-- ChartistJS -->
<script src='<c:url value="/resources/plugins/chartist/js/chartist.min.js"/>'></script>
<script src='<c:url value="/resources/plugins/chartist-plugin-tooltips/js/chartist-plugin-tooltip.min.js"/> '></script>



<script src='<c:url value="/resources/js/dashboard/dashboard-1.js"/> '></script>

</body>

</html>
