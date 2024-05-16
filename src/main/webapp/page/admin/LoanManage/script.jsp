<%@ page import="com.sonnguyen.individual.nhs.Constant.LoanStatus" %>
<script>
    $(function(){
        function fetchLoansData({status="PENDING", end, start}){
            return $.get("<%=request.getContextPath()%>/app/ajax/loans?status="+status);
        }
        function approveLoan(id){
            $.ajax({
                url:"<%=request.getContextPath()%>/app/ajax/loans",
                method:"PUT",
                data:{
                    id:id,
                    status:"<%=LoanStatus.APPROVED%>"
                }
            })
        }
        function rejectLoan(id){
            $.ajax({
                url:"<%=request.getContextPath()%>/app/ajax/loans",
                method:"PUT",
                data:{
                    id:id,
                    status:"<%=LoanStatus.REJECTED%>"
                }
            })
        }
        fetchLoansData("PENDING").done(data=>{
            $("#PENDING tbody").html(JSON.parse(data).map(loan=>{
                return `<tr>
                                <td>1</td>
                                <td>\${loan.customer.firstname} \${loan.customer.lastname}</td>
                                <td>\${loan.disbursementAccountNumber}</td>
                                <td>\${loan.createAt}</td>
                                <td>\${loan.amount}</td>
                                <td>\${loan.interestRate}</td>
                                <td>\${loan.term} Month</td>
                                <td>
                                    <button value="\${loan.id}"  class="btn btn-success approve">Approved</button>
                                    <button value="\${loan.id}"  class="btn btn-warning reject">Reject</button>
                                    <a href="./index.html" class="btn btn-warning">Detail</a>
                                </td>
                            </tr>`;
            }).join(''));
        })

        var triggerTabList = [].slice.call(document.querySelectorAll('#loan-status a'));
        triggerTabList.forEach(function (triggerEl) {
            var tabTrigger = new bootstrap.Tab(triggerEl)
            var status = triggerEl.getAttribute("data-status");
            $("#"+status+" tbody").on("click",e=>{
                if(e.target.classList.contains("approve")){
                    approveLoan(e.target.value)
                }else if(e.target.classList.contains("reject")){
                    rejectLoan(e.target.value)
                }
            })

            triggerEl.addEventListener('click', function (event) {
                fetchLoansData(status).done(data=>{
                    $("#"+status+" tbody").html(JSON.parse(data).map(loan=>{
                        return `<tr>
                                <td>1</td>
                                <td>\${loan.customer.firstname} \${loan.customer.lastname}</td>
                                <td>\${loan.disbursementAccountNumber}</td>
                                <td>\${loan.createAt}</td>
                                <td>\${loan.amount}</td>
                                <td>\${loan.interestRate}</td>
                                <td>\${loan.term} Month</td>
                                <td>
                                    \${status=="PENDING"&&`<button value='\${loan.id}' class='btn btn-success approve'>Approve</button>
                        <button  value="\${loan.id}" class="btn btn-warning">Reject</button>`}
                                    <a href="./index.html" class="btn btn-warning">Detail</a>
                                </td>
                            </tr>`;
                    }).join(''));
                })
                event.preventDefault()
                tabTrigger.show()
            })

        });


    })
</script>