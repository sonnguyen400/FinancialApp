<%@ page import="com.sonnguyen.individual.nhs.constant.AccountType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ex" uri="/WEB-INF/custom.tld"%>
<div class="row justify-content-center">
    <div class="col-sm-12 col-md-8 col-lg-6">
       <div class="row">
           <div class="col-12">
               <div class="card">
                   <div class="card-body">
                       <div class="card-subtitle">Add Coop-Account</div>
                       <div class="row">
                           <div class="col-12">
                               <div class="d-flex p-2">
                                   <input class="flex-grow-1 mr-1 form-control" id="searchInp" placeholder="Enter customer's email">
                                   <button id="searchBtn" class="btn btn-primary"><i class="fi fi-sr-search text-white"></i></button>
                               </div>
                           </div>
                       </div>
                       <form id="coop" class="row p-2" method="post" action="${pageContext.request.contextPath}/app/account/new?action=addmember"></form>
                   </div>
               </div>
           </div>
       </div>
        <div class="row">
            <div class="col-12">
                <div id="searchResult"></div>
            </div>
        </div>
    </div>
</div>

