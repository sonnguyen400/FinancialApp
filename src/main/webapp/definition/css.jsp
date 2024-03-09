<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tilesx:useAttribute name="css" id="items" classname="java.util.List"/>
<c:forEach var="item" items="${items}">
    <link href='<c:url value="${item}"/>' rel="stylesheet">
</c:forEach>