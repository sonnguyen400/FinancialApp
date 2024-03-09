<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%--
  Created by IntelliJ IDEA.
  User: Acer Swift 3
  Date: 3/9/2024
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<tilesx:useAttribute name="props" id="items" classname="java.util.List"/>
<c:forEach var="item" items="${items}">
  <${item}/>
</c:forEach>
