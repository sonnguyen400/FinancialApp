<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi"/>
<fmt:setBundle basename="lang"/>
<div class="nk-sidebar">
    <div class="nk-nav-scroll">
        <ul class="metismenu" id="menu">
            <li>
                <a class="has-arrow" href="${pageContext.request.contextPath}/" aria-expanded="false">
                    <i class="fi fi-sr-house-chimney"></i><span class="nav-text"><fmt:message key="home"/> </span>
                </a>
            </li>
            <li>
                <a class="has-arrow" href="${pageContext.request.contextPath}/app/history" aria-expanded="false">
                    <i class="fi fi-sr-time-past"></i><span class="nav-text"><fmt:message key="history"/></span>
                </a>
            </li>
            <li>
                <a class="has-arrow" href="${pageContext.request.contextPath}/app/transfer" aria-expanded="false">
                    <i class="fi fi-sr-money-bill-transfer"></i></i><span class="nav-text"><fmt:message key="transfer"/> </span>
                </a>
            </li>
            <li>
                <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                    <i class="fi fi-sr-loan"></i><span class="nav-text"><fmt:message key="loan"/></span>
                </a>
                <ul aria-expanded="false">
                    <li><a href="${pageContext.request.contextPath}/app/loan"><fmt:message key="loan.management"/> </a></li>
                    <li><a href="${pageContext.request.contextPath}/app/loan/create"><fmt:message key="loan.creation"/> </a></li>
                </ul>
            </li>
            <li>
                <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                    <i class="fi fi-sr-piggy-bank"></i><span class="nav-text"><fmt:message key="savings"/> </span>
                </a>
                <ul aria-expanded="false">
                    <li><a href="${pageContext.request.contextPath}/app/saving"><fmt:message key="savings.management"/> </a></li>
                    <li><a href="${pageContext.request.contextPath}/app/saving/create"><fmt:message key="savings.creation"/> </a></li>
                </ul>
            </li>

        </ul>
    </div>
</div>