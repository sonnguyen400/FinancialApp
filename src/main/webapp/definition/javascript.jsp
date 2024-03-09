<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@include file="/taglib/taglib.jsp"%>
<tilesx:useAttribute name="script" id="list" classname="java.util.List"/>
<c:forEach var="item" items="${list}">
    <script src='<c:url value="${item}"/>'></script>
</c:forEach>
