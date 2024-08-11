<%@ page import="static com.sonnguyen.individual.nhs.utils.RequestUtils.ERROR_MESSAGE" %>
<%@ page import="com.sonnguyen.individual.nhs.dto.Message" %>
<%@include file="/taglib/taglib.jsp" %>
<%@ taglib prefix="ex" uri="/WEB-INF/custom.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi"/>
<fmt:setBundle basename="lang"/>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register</title>
    <link rel='stylesheet'
          href='<c:url value="https://cdn-uicons.flaticon.com/2.1.0/uicons-solid-rounded/css/uicons-solid-rounded.css"/>'>
    <%@include file="/taglib/header.jsp" %>
    <link rel="stylesheet" href='<c:url value="/resources/plugins/sweetalert/css/sweetalert.css"/>'>
</head>
<body>

<jsp:useBean id="message" scope="request" class="com.sonnguyen.individual.nhs.dto.Message"/>
<c:if test="${message!=null&&message.type.name()=='SUCCESS'}">
    <ex:alert type="SUCCESS" link="Login" href="<c:url/>">jdftftu</ex:alert>
</c:if>

<div class="d-flex justify-content-center align-items-center" style="height: 100vh">
    <div class="card mw-570 w-100">
        <div class="card-body mw-570 w-100">
            <h4 class="text-center"><fmt:message key="register"/></h4>
            <h2 class="text-center">Harmony U</h2>
            <p class="text-black-50 text-sm-center "><fmt:message key="page.register.header.slogan"/> </p>
            <form id="form" method="post">
                <!-- Part1 -->
                <div class="part">
                    <div class=" form-group">
                        <label class="pb-2" for="firstname"><fmt:message key="page.register.field.firstname"/> </label>
                        <input id="firstname" name="firstname" class="form-control" data-rule="required" type="text" />
                        <span class="invalid-feedback animated fadeInDown"></span>
                    </div>
                    <div class=" form-group">
                        <label class="pb-2" for="lastname"><fmt:message key="page.register.field.lastname"/> </label>
                        <input id="lastname" name="lastname" class="form-control" data-rule="required" type="text"/>
                        <span class="invalid-feedback animated fadeInDown"></span>
                    </div>
                    <div class="form-group">
                        <label class="pb-2" for="dob"><fmt:message key="page.register.field.dateOfBirth"/> </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text bg-primary-darken-5"><i
                                        class="text-white fi-sr-cake-birthday"></i></div>
                            </div>
                            <input name="dateOfBirth" id="dob" class="form-control" data-rule="agemin(18)"
                                   type="date"/>
                        </div>
                        <span class="invalid-feedback animated fadeInDown"></span>
                    </div>
                    <div class="form-group">
                        <label class="pb-2" for="dob"><fmt:message key="page.register.field.gender"/></label>
                        <select name="gender" class="form-control">
                            <option>Male</option>
                            <option>Female</option>
                            <option>Other</option>
                        </select>
                    </div>
                </div>
                <!-- Part2 -->
                <div class="part">
                    <div class="col form-group pb-4">
                        <label class="pb-2" for="email"><fmt:message key="page.register.field.email"/> </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text bg-primary-darken-5"><i
                                        class="text-white fi fi-sr-envelope"></i></div>
                            </div>
                            <input id="email" class="form-control" name="email" data-rule="email" type="text"/>
                        </div>
                        <span class="invalid-feedback animated fadeInDown"></span>
                    </div>
                    <div class="col form-group pb-4">
                        <label class="pb-2" for="dob"><fmt:message key="page.register.field.phoneNumber"/> </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text bg-primary-darken-5"><i
                                        class="text-white fi-sr-phone-call"></i></div>
                            </div>
                            <input id="phoneNumber" class="form-control" name="phone" data-rule="none" name="phone"
                                   type="text" />
                        </div>
                        <span class="invalid-feedback animated fadeInDown"></span>
                    </div>
                    <div class="col form-group pb-4">
                        <label class="pb-2" for="dob"><fmt:message key="page.register.field.id"/></label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text bg-primary-darken-5"><i
                                        class="text-white fi-sr-id-badge"></i></div>
                            </div>
                            <input class="form-control" name="social_security_number" data-rule="minlength(8)"
                                   type="text"/>
                        </div>
                        <span class="invalid-feedback animated fadeInDown"></span>
                    </div>

                </div>
                <!-- Part3 -->
                <div class="part">
                    <h6 class="Agree with terms"></h6>
                    <div class="col form-group pb-4">
                        <label class="pb-2" for="dob"><fmt:message key="page.register.field.username"/></label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text bg-primary-darken-5"><i
                                        class="text-white fi-sr-user"></i>
                                </div>
                            </div>
                            <input id="phoneNumber" class="form-control" name="username" data-rule="none"
                                   name="username" type="text"/>
                        </div>
                        <span class="invalid-feedback"></span>
                    </div>
                    <div class="col form-group pb-4">
                        <label class="pb-2" for="password"><fmt:message key="page.register.field.password"/></label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text bg-primary-darken-5"><i
                                        class="text-white fi-sr-lock"></i>
                                </div>
                            </div>
                            <input id="password" class="form-control" name="password" data-rule="none" type="text"/>
                        </div>
                        <span class="invalid-feedback"></span>
                    </div>
                    <div class="col form-group pb-4">
                        <label class="pb-2" for="PIN"><fmt:message key="page.register.field.PIN"/></label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text bg-primary-darken-5"><i
                                        class="text-white fi-sr-lock"></i>
                                </div>
                            </div>
                            <input id="PIN" class="form-control" name="pin" data-rule="none" type="text"/>
                        </div>
                        <span class="invalid-feedback"></span>
                    </div>
                    <div class="col form-group pb-4">
                        <label class="pb-2" for="dob"><fmt:message key="page.register.field.accountNumber"/> </label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text bg-primary-darken-5"><i
                                        class="text-white fi-sr-lock"></i>
                                </div>
                            </div>
                            <input id="AccountNUmber" class="form-control" name="accountNumber" data-rule="none"
                                   type="text"/>
                        </div>
                        <span class="invalid-feedback"></span>
                    </div>
                </div>
                <div class="d-flex flex-row-reverse">
                    <button class="btn btn-primary mx-2" type="button" id="nextStep"><fmt:message key="page.register.button.next"/></button>
                    <button class="btn btn-success mx-2" type="button" id="submitBtn"><fmt:message key="page.register.button.register"/></button>
                    <button class="btn btn-outline-primary mx-2" type="button" id="preStep"><fmt:message key="page.register.button.pre"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/taglib/basescript.jsp" %>
<script src='<c:url value="/resources/plugins/sweetalert/js/sweetalert.min.js"/>'></script>
<script src='<c:url value="/resources/js/FormValidator.js"/> '></script>
<script type="module">
    $(function () {
        stepFormValidate($("#form"))
        <%
            if (request.getAttribute(ERROR_MESSAGE) != null) {
                out.print("sweetAlert(\"Oops...\", \" "+ request.getAttribute(ERROR_MESSAGE)+ " \", \"error\");");
            }
        %>
    })
</script>
</body>
</html>