<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="ex" uri="/WEB-INF/custom.tld" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="loan" scope="request" class="com.sonnguyen.individual.nhs.model.Loan"/>
<jsp:useBean id="alert" scope="request" class="com.sonnguyen.individual.nhs.dto.Alert"/>
<ex:alert type="${alert.type.name()}" link="${alert.link}" href="${alert.href}">${alert.message}</ex:alert>
<div class="row justify-content-center">
    <div class="col-md-6 col-sm-12">
        <div class="card" >
            <div style="border-radius: inherit;min-width:365px" class="p-4 bg-primary text-white" >
                <h3 class="text-white">${loan.amount}
                </h3>
                <p class="text-white-50">${loan.createAt}</p>
                <div class="row">
                    <div class="col">
                        <div class="text-white-50">Interest Rate</div>
                        <p ><fmt:formatNumber type="percent" value="${loan.interestRate/100}"  /> / year</p>
                    </div>
                    <div class="col align-self-end">
                        <div class="text-white-50">Term</div>
                        <p >${loan.term} Month</p>
                    </div>
                </div>
            </div>
            <%
                LocalDateTime localDateTime=LocalDateTime.ofInstant(Instant.ofEpochMilli(loan.getCreateAt().getTime()), ZoneId.of("GMT+7"));
                long currentDays=ChronoUnit.DAYS.between(localDateTime,LocalDateTime.now(ZoneId.of("GMT+7")));
                long  maturityDays=ChronoUnit.DAYS.between(localDateTime,localDateTime.plusMonths(loan.getTerm()));
                double process=currentDays*100.0/maturityDays;
            %>
            <div class="card-body">
                <div class="w-100">
                    <div class="progress" style="height: 6px;">
                        <div class="progress-bar bg-primary" style="width: <%=process<=100?process:100%>%;" role="progressbar">
                        </div>
                    </div>
                    <div class="d-flex justify-content-between text-sm-4 pt-1">
                        <span><%=localDateTime.format(DateTimeFormatter.ISO_DATE)%></span>
                        <span><%=localDateTime.plusMonths(6).format(DateTimeFormatter.ISO_DATE)%></span>
                    </div>
                </div>
                <div class="d-flex justify-content-between py-2">
                    <span>Update At</span>
                    <span><%=localDateTime.format(DateTimeFormatter.ISO_DATE)%></span>
                </div>
                <div class="d-flex justify-content-between py-2">
                    <span>To Date</span>
                    <span><%=localDateTime.plusMonths(6).format(DateTimeFormatter.ISO_DATE)%></span>
                </div>


                <div class="d-flex justify-content-end py-2">
                    <div class="bt-4">
                        <a href="<%=request.getContextPath()+"/app/loan/payment?id="+loan.getId()%>" class="btn bg-primary text-white">Complete</a>
                    </div>
                </div>
            </div>


        </div>

    </div>


</div>




