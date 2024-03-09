<div class="row">
    <div class="col">
        <div class="card">
            <div class="card-body">
                <form>
                    <div class="form-group col">
                        <label for="amount" class="col-form-label">
                            <span>Organize</span>
                            <span class="text-danger">*</span>
                        </label>
                        <select class="form-control input-default">
                            <option value="">Mb</option>
                        </select>
                    </div>
                    <div class="form-group col">
                        <label for="account_number" class="col-form-label">
                            <span>Receiver account number</span>
                            <span class="text-danger">*</span>
                        </label>
                        <input type="text" name="account_number" class="form-control input-default"
                               id="receiver_account_number">
                    </div>
                    <div class="form-group col">
                        <label for="amount" class="col-form-label">
                            <span>Amount</span>
                            <span class="text-danger">*</span>
                        </label>
                        <input type="number" min=0 name="amount" class="form-control input-default">
                    </div>
                    <div class="form-group col">
                        <label for="Message" class="col-form-label">
                            <span>Message</span>
                            <span class="text-danger">*</span>
                        </label>
                        <input type="text" name="Message" class="form-control input-default">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Transfer infor -->
    <div class="col">
        <div class="card">
            <div class="card-body">
                <div class="col">
                    <div id="current-balance col-form-label">Current Balance</div>
                    <h2 class="text-xxl blue mt-2">125.000.000</h2>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="col">
                    <label id="current-balance col-form-label">Receiver Information</label>
                    <input class="input-default form-control" disabled id="receiver_name">
                </div>
            </div>
        </div>
        <div class="bootstrap-modal">
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#EnterPIN">Continue</button>
        </div>


    </div>
</div>
<div class="modal fade" id="EnterPIN" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Enter your PIN</h5>
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span>
                </button>
            </div>

            <form action="POST">
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col">
                                <input type="PIN" maxlength="1" class="p-2 text-lg form-control input-default">
                            </div>
                            <div class="col">
                                <input type="PIN" maxlength="1" class="p-2 text-lg form-control input-default">
                            </div>
                            <div class="col">
                                <input type="PIN" maxlength="1" class="p-2 text-lg form-control input-default">
                            </div>
                            <div class="col">
                                <input type="PIN" maxlength="1" class="p-2 text-lg form-control input-default">
                            </div>
                            <div class="col">
                                <input type="PIN" maxlength="1" class="p-2 text-lg form-control input-default">
                            </div>
                            <div class="col">
                                <input type="PIN" maxlength="1" class="p-2 text-lg form-control input-default">
                            </div>
                            <input type="hidden" name="PIN_ENTERED">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <div class="bootstrap-modal">
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#EnterOTP">Continue</button>
                    </div>
                </div>
            </form>


        </div>
    </div>
</div>
<div class="modal fade" id="EnterOTP" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Enter OTP</h5>
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span>
                </button>
            </div>
            <form action="POST">
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col">
                                <input type="PIN" maxlength="1" class="p-2 text-lg form-control input-default">
                            </div>
                            <div class="col">
                                <input type="PIN" maxlength="1" class="p-2 text-lg form-control input-default">
                            </div>
                            <div class="col">
                                <input type="PIN" maxlength="1" class="p-2 text-lg form-control input-default">
                            </div>
                            <div class="col">
                                <input type="PIN" maxlength="1" class="p-2 text-lg form-control input-default">
                            </div>
                            <div class="col">
                                <input type="PIN" maxlength="1" class="p-2 text-lg form-control input-default">
                            </div>
                            <div class="col">
                                <input type="PIN" maxlength="1" class="p-2 text-lg form-control input-default">
                            </div>
                            <input type="hidden" name="PIN_ENTERED">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <div class="bootstrap-modal">
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                data-target="#EnterOTP">Continue</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    (function ($) {
        "use strict"
        new quixSettings({
            version: "light", //2 options "light" and "dark"
            layout: "horizontal", //2 options, "vertical" and "horizontal"
            navheaderBg: "color_1", //have 10 options, "color_1" to "color_10"
            headerBg: "color_1", //have 10 options, "color_1" to "color_10"
            sidebarStyle: "vertical", //defines how sidebar should look like, options are: "full", "compact", "mini" and "overlay". If layout is "horizontal", sidebarStyle won't take "overlay" argument anymore, this will turn into "full" automatically!
            sidebarBg: "color_1", //have 10 options, "color_1" to "color_10"
            sidebarPosition: "static", //have two options, "static" and "fixed"
            headerPosition: "fixed", //have two options, "static" and "fixed"
            containerLayout: "wide",  //"boxed" and  "wide". If layout "vertical" and containerLayout "boxed", sidebarStyle will automatically turn into "overlay".
            direction: "ltr" //"ltr" = Left to Right; "rtl" = Right to Left
        });
    })(jQuery);
</script>