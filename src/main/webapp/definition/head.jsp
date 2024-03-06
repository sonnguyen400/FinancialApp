<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@include file="/taglib/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <title>
    <link rel="stylesheet" href='<c:url value="/resources/plugins/chartist/css/chartist.min.css"/>'>
    <link rel="stylesheet" href='<c:url value="/resources/plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css"/>'>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link href='<c:url value="/resources/css/style.css"/>' rel="stylesheet">
    <tiles:insertAttribute name="title"/>
    <tiles:insertAttribute name="props"/>
  </title>
</head>