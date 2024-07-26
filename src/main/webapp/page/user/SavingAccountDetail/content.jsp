<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.sonnguyen.individual.nhs.Constant.SavingType" %>
<%@ page import="com.sonnguyen.individual.nhs.Constant.Rollover" %>
<jsp:useBean id="savings" scope="request" type="com.sonnguyen.individual.nhs.model.SavingsInfo"/>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row justify-content-center">
    <div class="col-md-6 col-sm-12">
        <div class="card" >
            <div style="border-radius: inherit;min-width:365px" class="p-4 bg-primary text-white" >
                <h3 class="text-white">${savings.account.balance}
                </h3>
                <p class="text-white-50">${savings.account.openDate}</p>
                <div class="row">
                    <div class="col">
                        <div class="text-white-50">Interest Rate</div>
                        <p ><fmt:formatNumber type="percent" value="${savings.interestRate/100}"  /> / year</p>
                    </div>
                    <div class="col align-self-end">
                        <div class="text-white-50">Term</div>
                        <p >${savings.term} Month</p>
                    </div>
                </div>
            </div>
            <%
                LocalDateTime localDateTime=LocalDateTime.ofInstant(Instant.ofEpochMilli(savings.getAccount().getOpenDate().getTime()), ZoneId.of("GMT+7"));
                long currentDays=ChronoUnit.DAYS.between(localDateTime,LocalDateTime.now(ZoneId.of("GMT+7")));
                long  maturityDays=ChronoUnit.DAYS.between(localDateTime,localDateTime.plusMonths(savings.getTerm()));
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
                <div class="d-flex justify-content-between py-2 mt-2">
                    <span>Estimated Interest at Maturity</span>
                    <span><%=(savings.getInterestRate().doubleValue()/100)*savings.getAccount().getBalance().doubleValue()*ChronoUnit.MONTHS.between(LocalDateTime.now(),localDateTime)/12%></span>
                </div>
                <div class="d-flex justify-content-between py-2">
                    <span>Accrued Interest to Date</span>
                    <span><%=savings.getInterestRate().doubleValue()/100* savings.getAccount().getBalance().doubleValue()*savings.getTerm()/12%></span>
                </div>
                <div class="d-flex justify-content-between py-2">
                    <span>Create At</span>
                    <span><%=localDateTime.format(DateTimeFormatter.ISO_DATE)%></span>
                </div>
                <div class="d-flex justify-content-between py-2">
                    <span>To Date</span>
                    <span><%=localDateTime.plusMonths(6).format(DateTimeFormatter.ISO_DATE)%></span>
                </div>
                <div class="d-flex justify-content-between py-2">
                    <span>Type</span>
                    <span>
                        <c:if test="${savings.type==1}">
                            Term Deposit
                        </c:if>
                        <c:if test="${savings.type==2}">
                            Demand Deposit
                        </c:if>
                    </span>
                </div>
                <div class="d-flex justify-content-between py-2">
                    <span>Rollover</span>
                    <span>
                        <c:if test="${savings.rollover==1}">
                            Withdraw Entire
                        </c:if>
                        <c:if test="${savings.rollover==2}">
                            Rollover Principal
                        </c:if>
                        <c:if test="${savings.rollover==3}">
                            Rollover All
                        </c:if>
                    </span>
                </div>

            </div>


        </div>
        <div class="bt-4">
            <button class="btn bg-primary">Settle</button>
        </div>
    </div>


</div>




