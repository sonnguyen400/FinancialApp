<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.sonnguyen.individual.nhs.utils.SessionUtils" %>
<%@ page import="com.sonnguyen.individual.nhs.security.UserDetailImp" %>
<%UserDetailImp account=SessionUtils.getPrincipal(request);%>
<fmt:setLocale value="vi"/>
<fmt:setBundle basename="lang"/>
<div class="header">
    <div class="header-content clearfix">

        <div class="nav-control">
            <div class="hamburger">
                <span class="toggle-icon"><i class="fi fi-sr-menu-burger"></i></span>
            </div>
        </div>
        <div class="header-left">

        </div>
        <div class="header-right">
            <ul class="clearfix">
                <li class="icons dropdown d-none d-md-flex">
                    <a href="javascript:void(0)" class="log-user"  data-toggle="dropdown">
                        <i class="fi fi-sr-earth-americas"></i>
                        <i class="fi fi-sr-caret-down"></i>
                    </a>
                    <div class="drop-down dropdown-language animated fadeIn  dropdown-menu">
                        <div class="dropdown-content-body">
                            <form method="get" action="${pageContext.request.contextPath}/language">
                                <input type="hidden" name="language_page_uri" value="${pageContext.request.requestURL}">
                                <ul>
                                    <li><button name="lang" value="vi">Vietnamese</button></li>
                                    <li><button name="lang" value="en">English</button></li>
                                </ul>
                            </form>
                        </div>
                    </div>
                </li>
                <li class="icons dropdown">
                    <div class="user-img c-pointer position-relative"   data-toggle="dropdown">
                        <span class="activity active"></span>
                        <img src="<c:url value="/resources/images/avatar/unknown.webp"/>" height="40" width="40" alt="">
                    </div>
                    <div class="drop-down dropdown-profile animated fadeIn dropdown-menu">
                        <div class="dropdown-content-body">
                            <ul>
                                <li>
                                    <a href="<%=request.getContextPath()%>/app/personal"><i class="icon-user"></i> <span>
                                        <%=account.getCustomer().getFirstname()+"  "+account.getCustomer().getLastname()%>
                                    </span></a>
                                </li>


                                <hr class="my-2">

                                <li><a href="<%=request.getContextPath()%>/logout"><i class="icon-key"></i> <span><fmt:message key="logout"/></span></a></li>
                            </ul>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>