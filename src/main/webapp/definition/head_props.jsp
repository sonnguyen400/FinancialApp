<%--
  Created by IntelliJ IDEA.
  User: Acer Swift 3
  Date: 3/8/2024
  Time: 6:53 PM
  To change this template use File | Settings | File Templates.
--%>
<tilesx:useAttribute name="props" id="items" classname="java.util.List"/>
<c:forEach var="item" items="${items}">
  <tiles:insertAttribute value="${item}" flush="true"/>
</c:forEach>