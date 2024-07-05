<%@ page import="com.sonnguyen.individual.nhs.Model.Account" %>
<%@ page import="com.sonnguyen.individual.nhs.Utils.SessionUtils" %>
<%@ page import="com.sonnguyen.individual.nhs.Model.Login" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%
    Login account = SessionUtils.getPrincipal(request);
    Account principalAccount = (Account) request.getAttribute("account");
%>

<div class="row">
    <div class="col-xl-3">
        <a href="<%=request.getContextPath()%>/app/account/manage">
            <div class="card gradient-1">
                <div class="card-body">
                    <div class="text-center">
                        <span class="text-md-11 opacity-5 text-white"><i class="fi fi-sr-sack-dollar"></i></span>
                        <h6 class="mt-2 mb-2 text-white-50 text-white">Balance</h6>
                        <h3 class="text-white"><%=principalAccount.getBalance()%>
                        </h3>
                        <button class="btn gradient-3 btn-lg border-0 btn-rounded px-5">
                            <i class="fi fi-sr-eye"></i>
                            <%--                <i class="fi fi-sr-eye-crossed"></i>--%>
                        </button>
                    </div>
                </div>
            </div>
        </a>
    </div>

    <%--    Carousel--%>
    <div class="col-xl-6">
        <div id="carouselExampleCaptions" style="max-height: 145px" class="carousel slide card"
             style="overflow: hidden;"
             data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
                <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="./userhome.jpg" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>First slide label</h5>
                        <p>Some representative placeholder content for the first slide.</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="./userhome.jpg" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Second slide label</h5>
                        <p>Some representative placeholder content for the second slide.</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="./userhome.jpg" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Third slide label</h5>
                        <p>Some representative placeholder content for the third slide.</p>
                    </div>
                </div>
            </div>
            <button class="slide-control left" data-target="#carouselExampleCaptions" data-slide="prev">
                <i class="fa-solid fa-angle-left"></i>
                <span class="sr-only">Previous</span>
            </button>
            <button class="slide-control right" data-target="#carouselExampleCaptions" data-slide="next">
                <i class="fa-solid fa-angle-right"></i>
            </button>
        </div>
    </div>
</div>

<%--Toolbar--%>
<div class="row ">
    <a href="${pageContext.request.contextPath}/app/transfer" class="col-md-2 col-sm-6">
        <div class="card react-widget gradient-2">
            <div class="gutter-md-2">
                <div class="col">
                    <h4 class="text-white text-md-8">Transfer</h4>
                    <span class="float-right text-md-11 opacity-5 "><i
                            class="fi fi-sr-money-bill-transfer"></i></span>
                </div>
            </div>
        </div>
    </a>
    <a href="${pageContext.request.contextPath}/app/history" class="col-md-2 col-sm-6">
        <div class="card react-widget gradient-3">
            <div class="gutter-md-2">
                <div class="col">
                    <h4 class="text-white text-md-8">History</h4>
                    <span class="float-right text-md-11 opacity-5 "><i
                            class="fi fi-sr-clock"></i></span>
                </div>
            </div>
        </div>
    </a>
    <a href="${pageContext.request.contextPath}/app/loan" class="col-md-2 col-sm-6">
        <div class="card react-widget gradient-3">
            <div class="gutter-md-2">
                <div class="col">
                    <h4 class="text-white text-md-8">Loan</h4>
                    <span class="float-right text-md-11 opacity-5 ">
                        <i class="fi fi-sr-hand-holding-usd"></i>
                    </span>
                </div>
            </div>
        </div>
    </a>
    <a href="<%=request.getContextPath()%>/app/saving" class="col-md-2 col-sm-6">
        <div class="card react-widget gradient-4">
            <div class="gutter-md-2">
                <div class="col">
                    <h4 class="text-white text-md-8">Savings</h4>
                    <span class="float-right text-md-11 opacity-5 ">
                        <i class="fi fi-sr-piggy-bank"></i>
                    </span>
                </div>
            </div>
        </div>
    </a>

</div>