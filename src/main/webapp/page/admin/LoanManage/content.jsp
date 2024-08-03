<%@ page import="com.sonnguyen.individual.nhs.constant.LoanStatus" %>
<div class="card">
    <div class="card-body">
        <h4 class="card-title">Default Tab</h4>
        <!-- Nav tabs -->
        <div class="default-tab">
            <ul class="nav nav-tabs mb-3" id="loans" role="tablist">
                <li class="nav-item"><a class="nav-link active" data-toggle="tab" data-status="<%=LoanStatus.PENDING%>" href="#<%=LoanStatus.PENDING%>"><%=LoanStatus.PENDING%></a>
                </li>
                <li class="nav-item"><a class="nav-link" data-toggle="tab" data-status="<%=LoanStatus.APPROVED%>" href="#<%=LoanStatus.APPROVED%>"><%=LoanStatus.APPROVED%></a>
                </li>
                <li class="nav-item"><a class="nav-link" data-toggle="tab" data-status="<%=LoanStatus.REJECTED%>" href="#<%=LoanStatus.REJECTED%>"><%=LoanStatus.REJECTED%></a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade show active" id="<%=LoanStatus.PENDING%>" role="tabpanel">
                    <table class="table table-primary table-hover table-striped">
                        <thead>
                        <td></td>
                        <td><a>Customer</a></td>
                        <td>Distri Account</td>
                        <td>Create At</td>
                        <td>Amount</td>
                        <td>Interest Rate</td>
                        <td>Term</td>
                        <td>Action</td>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="<%=LoanStatus.APPROVED%>">
                    <table class="table table-primary table-hover table-striped">
                        <thead>
                        <td></td>
                        <td><a>Customer</a></td>
                        <td>Distri Account</td>
                        <td>Create At</td>
                        <td>Amount</td>
                        <td>Interest Rate</td>
                        <td>Term</td>
                        <td>Action</td>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="<%=LoanStatus.REJECTED%>">
                    <table class="table table-primary table-hover table-striped">
                        <thead>
                        <td></td>
                        <td><a>Customer</a></td>
                        <td>Distri Account</td>
                        <td>Create At</td>
                        <td>Amount</td>
                        <td>Interest Rate</td>
                        <td>Term</td>
                        <td>Action</td>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>