<%@ page import="static com.sonnguyen.individual.nhs.Utils.Constants.PIN" %>
<%@ page import="static com.sonnguyen.individual.nhs.Utils.Constants.OTP" %>
<div class="row  justify-content-center">
    <div class="col-lg-3 mx-auto" >
        <div class="card">
            <div class="card-body">
                <form method="post" class="" action="<%=request.getContextPath()%>/app/otp">
                    <div class="form-group">
                        <label for="OTP">Enter OTP</label>
                        <input id="OTP" name="<%=OTP%>" class="form-control">
                        <div class="d-flex justify-content-end mt-3"><button type="submit" class=" btn btn-primary">Continue</button></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>