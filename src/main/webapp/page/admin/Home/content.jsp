<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row gutter-lg-4 gutter-md-3 gutter-sm-1">
    <div class=" col-12">
        <h4 class="text-primary">Admin Home</h4>
    </div>
    <div class=" col-12">

        <div class="row ">
            <a href="${pageContext.request.contextPath}/admin/loan-manage" class="col-md-3 col-sm-6">
                <div class="card react-widget gradient-2">
                    <div class="gutter-md-2">
                        <div class="col">
                            <h4 class="text-white text-md-8"><fmt:message key="loan.manage"/></h4>
                            <span class="float-right text-md-11 opacity-5 "><i
                                    class="fi fi-sr-money-bill-transfer"></i></span>
                        </div>

                    </div>
                </div>
            </a>
        </div>
    </div>

</div>