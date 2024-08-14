<%@ page import="com.sonnguyen.individual.nhs.constant.LoanStatus" %>
<script>
    function approveLoan(e) {
        console.log(e.target.value)
        <%--$.ajax({--%>
        <%--    url: baseUrl,--%>
        <%--    method: "PUT",--%>
        <%--    data: {--%>
        <%--        id: e.target.value,--%>
        <%--        status: "<%=LoanStatus.APPROVED%>"--%>
        <%--    }--%>
        <%--})--%>
    }

    function rejectLoan(id) {
        $.ajax({
            url: baseUrl,
            method: "PUT",
            data: {
                id: id,
                status: "<%=LoanStatus.REJECTED%>"
            }
        })
    }
    $(function () {
        const baseUrl = "<%=request.getContextPath()%>/ajax/loans";

        function fetchLoansData({status, end, start}) {
            return $.get(`\${baseUrl}?status=\${status}`);
        }



        var triggerTabList = $("#loans a")
        triggerTabList.on("shown.bs.tab", function (e) {
            $.get(`\${baseUrl}?status=\${this.getAttribute("data-status")}`, (data) => {
                data=JSON.parse(data)
                console.log(data)
                $(this.hash+" tbody").html(
                    data.map(item=>` <tr>
                                <td>\${item.id}</td>
                                <td>\${item.customer.firstname+" "+item.customer.lastname}</td>
                                <td>\${item.disbursementAccountNumber}</td>
                                <td>\${item.createAt}</td>
                                <td>\${item.amount}</td>
                                <td>\${item.interestRate}%</td>
                                <td>\${item.term} Month</td>
                                <td>
                                    \${item.status==2?`<button class='btn btn-success' name='approve' value='\${item.id}' type='submit'>Approved</button> <button onclick='{(e)=>rejectLoan(\${item.id})}' class='btn btn-warning'>Reject</button>`:""}
                                    <a href=<%=request.getContextPath()%>/admin/loan/detail?id=\${item.id} class="btn btn-warning">Detail</a>
                                </td>
                            </tr> `).join("")
                )
            })
        })
        $.get(`\${baseUrl}?status=PENDING`, (data) => {
            data=JSON.parse(data)
            $("#PENDING tbody").html(
                data.map(item=>` <tr>
                                <td>\${item.id}</td>
                                <td>\${item.customer.firstname+" "+item.customer.lastname }</td>
                                <td>\${item.disbursementAccountNumber}</td>
                                <td>\${item.createAt}</td>
                                <td>\${item.amount}</td>
                                <td>\${item.interestRate}%</td>
                                <td>\${item.term} Month</td>
                                <td>
                                     \${item.status==2?`<button class='btn btn-success' value='\${item.id}' name='approve' type='submit'>Approved</button> <button onclick='{(e)=>rejectLoan(\${item.id})}' class='btn btn-warning'>Reject</button>`:""}
                                    <a href=<%=request.getContextPath()%>/admin/loan/detail?id=\${item.id} class="btn btn-warning">Detail</a>
                                </td>
                            </tr> `).join("")
            )
        })


    })
</script>

