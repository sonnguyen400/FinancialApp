<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertAttribute name="navheader"/>
<tiles:insertAttribute name="header"/>
<div class="content-body" style="padding:0 ">
    <div id="content" class="container-fluid h-100">
        <tiles:insertAttribute name="content"/>
    </div>
</div>

