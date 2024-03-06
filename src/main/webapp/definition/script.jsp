<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Acer Swift 3
  Date: 3/8/2024
  Time: 6:37 PM
  To change this template use File | Settings | File Templates.
--%>

<tilesx:useAttribute name="scipts" id="items" classname="java.util.List"/>
<c:forEach var="item" items="${items}">
  <tiles:insertAttribute value="${item}" flush="true"/>
</c:forEach>