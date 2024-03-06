<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class="modal fade" id="EnterPIN" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><tiles:insertAttribute name="title"/>/h5>
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span>
                </button>
            </div>
            <tiles:insertAttribute name="content"/>
        </div>
    </div>
</div>