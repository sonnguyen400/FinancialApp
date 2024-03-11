<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertAttribute name="navheader"/>
<tiles:insertAttribute name="header"/>
<div class="content-body">
    <div id="content" class="container-fluid mt-3">
        <tiles:insertAttribute name="content"/>
    </div>
</div>

