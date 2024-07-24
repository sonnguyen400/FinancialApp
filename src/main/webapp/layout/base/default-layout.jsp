<%@include file="/taglib/taglib.jsp"%>
<tiles:insertAttribute name="navheader"/>
<tiles:insertAttribute name="header"/>
<tiles:insertAttribute name="sidebar"/>
<div class="content-body">
    <div class="container-fluid mt-3 h-100">
        <tiles:insertAttribute name="content"/>
    </div>
</div>

