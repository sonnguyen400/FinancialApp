<%@ page import="com.sonnguyen.individual.nhs.constant.LoanStatus" %>
<div class="card">
    <div class="card-body">
        <h4 class="card-title">Default Tab</h4>
        <!-- Nav tabs -->
        <div class="default-tab">
            <ul class="nav nav-tabs mb-3" id="loan-status" role="tablist">
                <li class="nav-item"><a class="nav-link active" data-toggle="tab" data-status="<%=LoanStatus.PENDING%>" href="#<%=LoanStatus.PENDING%>"><%=LoanStatus.PENDING.value%></a>
                </li>
                <li class="nav-item"><a class="nav-link" data-toggle="tab" data-status="<%=LoanStatus.APPROVED%>" href="#<%=LoanStatus.APPROVED%>"><%=LoanStatus.APPROVED%></a>
                </li>
                <li class="nav-item"><a class="nav-link" data-toggle="tab" data-status="<%=LoanStatus.REJECTED%>" href="#<%=LoanStatus.REJECTED%>"><%=LoanStatus.REJECTED%></a>
                </li>
                <li class="nav-item"><a class="nav-link" data-toggle="tab" href="#message">Message</a>
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
                            <tr>
                                <td>1</td>
                                <td>dhgj</td>
                                <td>0748131</td>
                                <td>13/31/2001 12:3:00</td>
                                <td>1000000</td>
                                <td>4.1%</td>
                                <td>6 Month</td>
                                <td>
                                    <button class="btn btn-success">Approved</button>
                                    <button class="btn btn-warning">Reject</button>
                                    <a href="./index.html" class="btn btn-warning">Detail</a>
                                </td>
                            </tr>
                        </tbody>

                    </table>
                    <div class="bootstrap-pagination">
                        <nav>
                            <ul class="pagination">
                                <li class="page-item"><a class="page-link" href="#" aria-label="Previous"><span
                                        aria-hidden="true">&laquo;</span> <span class="sr-only">Previous</span></a>
                                </li>
                                <li class="page-item"><a class="page-link" href="#">1</a>
                                </li>
                                <li class="page-item"><a class="page-link" href="#">2</a>
                                </li>
                                <li class="page-item"><a class="page-link" href="#">3</a>
                                </li>
                                <li class="page-item"><a class="page-link" href="#" aria-label="Next"><span
                                        aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span></a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="tab-pane fade" id="<%=LoanStatus.APPROVED%>">
                    <div class="p-t-15">
                        <h4>This is profile title</h4>
                        <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua,
                            retro synth master cleanse. Mustache cliche tempor.</p>
                        <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua,
                            retro synth master cleanse. Mustache cliche tempor.</p>
                    </div>
                </div>
                <div class="tab-pane fade" id="<%=LoanStatus.REJECTED%>">
                    <div class="p-t-15">
                        <h4>This is contact title</h4>
                        <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there
                            live the blind texts. Separated they live in Bookmarksgrove.</p>
                        <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there
                            live the blind texts. Separated they live in Bookmarksgrove.</p>
                    </div>
                </div>
                <div class="tab-pane fade" id="message">
                    <div class="p-t-15">
                        <h4>This is message title</h4>
                        <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua,
                            retro synth master cleanse. Mustache cliche tempor.</p>
                        <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu stumptown aliqua,
                            retro synth master cleanse. Mustache cliche tempor.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>