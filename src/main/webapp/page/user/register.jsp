<%@include file="/taglib/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register</title>
    <link rel='stylesheet'
          href='<c:url value="https://cdn-uicons.flaticon.com/2.1.0/uicons-solid-rounded/css/uicons-solid-rounded.css"/>'>
    <%@include file="/taglib/header.jsp" %>
</head>
<body>
<div class="d-flex justify-content-center align-items-center" style="height: 100vh">
    <div class="card mw-570 w-100">
        <div class="card-body mw-570 w-100">
            <h4 class="text-center">Register</h4>
            <h2 class="text-center">Harmony U</h2>
            <p class="text-black-50 text-sm-center ">Register and receive all your desired treats</p>
            <!-- Form 1 -->
            <form action="" style="display: block;" method="post" id="form1">
                <div class=" form-group">
                    <label class="pb-2" for="firstname">First name</label>
                    <input id="firstname" name="firstname" class="form-control" data-rule="required" type="text"
                           placeholder="First name"/>
                    <span class="invalid-feedback animated fadeInDown"></span>
                </div>
                <div class=" form-group">
                    <label class="pb-2" for="lastname">Last name</label>
                    <input id="lastname" name="lastname" class="form-control" data-rule="required" type="text"
                           placeholder="Last name"/>
                    <span class="invalid-feedback animated fadeInDown"></span>
                </div>
                <div class="form-group">
                    <label class="pb-2" for="dob">Date of birth</label>
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
                    <label class="pb-2" for="dob">Gender</label>
                    <select name="gender" data-rule="none" class="form-control">
                        <option>Male</option>
                        <option>Female</option>
                        <option>Other</option>
                    </select>
                </div>
            </form>
            <!-- Form 2 -->
            <form action="" style="display: none;" method="post" id="form2">
                <div class="col form-group pb-4">
                    <label class="pb-2" for="email">Email</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text bg-primary-darken-5"><i
                                    class="text-white fi fi-sr-envelope"></i></div>
                        </div>
                        <input id="email" class="form-control" name="email" data-rule="email" type="text"
                               placeholder="Email"/>
                    </div>
                    <span class="invalid-feedback animated fadeInDown"></span>
                </div>
                <div class="col form-group pb-4">
                    <label class="pb-2" for="dob">Phone number</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text bg-primary-darken-5"><i
                                    class="text-white fi-sr-phone-call"></i></div>
                        </div>
                        <input id="phoneNumber" class="form-control" name="phone" data-rule="none"
                               name="phone" type="text" placeholder="Phone number"/>
                    </div>
                    <span class="invalid-feedback animated fadeInDown"></span>
                </div>
                <div class="col form-group pb-4">
                    <label class="pb-2" for="dob">ID</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text bg-primary-darken-5"><i
                                    class="text-white fi-sr-id-badge"></i></div>
                        </div>
                        <input class="form-control" name="social_security_number" data-rule="minlength(8)" type="text"
                               placeholder="ID"/>
                    </div>
                    <span class="invalid-feedback animated fadeInDown"></span>
                </div>

            </form>
            <!-- Form 3 -->
            <form action="" style="display: none;" method="post" id="form3">
                <h6 class="Agree with terms"></h6>
                <div class="col form-group pb-4">
                    <label class="pb-2" for="dob">User name</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text bg-primary-darken-5"><i class="text-white fi-sr-user"></i>
                            </div>
                        </div>
                        <input id="phoneNumber" class="form-control" name="username" data-rule="none"
                               name="username" type="text" placeholder="User's name"/>
                    </div>
                    <span class="invalid-feedback"></span>
                </div>
                <div class="col form-group pb-4">
                    <label class="pb-2" for="dob">Password</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text bg-primary-darken-5"><i class="text-white fi-sr-lock"></i>
                            </div>
                        </div>
                        <input id="phoneNumber" class="form-control" name="password" data-rule="none"
                               name="password" type="text" placeholder="Password"/>
                    </div>
                    <span class="invalid-feedback"></span>
                </div>
            </form>
            <div class="d-flex flex-row-reverse">
                <button class="btn btn-primary mx-2" type="button" id="nextForm">Next</button>
                <button class="btn btn-success mx-2" type="button" id="submitAll">Send</button>
                <button class="btn btn-outline-primary mx-2" type="button" id="preForm">Pre</button>
            </div>
        </div>
    </div>

</div>
<%@include file="/taglib/basescript.jsp" %>
<script type="module">
    import Form  from "<c:url value="/resources/js/formvalidator.js"/>";
    $(function () {
        let form1 = Form("#form1",e=>{e.preventDefault()});
        let form2 = Form("#form2",e=>{e.preventDefault()});
        let form3 = Form("#form3",e=>{e.preventDefault()});
        let state = {
            form: form1,
            index: 1
        };
        let nextForm = $("#nextForm");
        let preForm = $("#preForm");
        let submitAll = $("#submitAll");

        let changeState = function (state_) {
            switch (state_) {
                case 1:
                    state.form = form1;
                    form1.form.css("display", "block");
                    form2.form.css("display", "none");
                    form3.form.css("display", "none");
                    nextForm.css("display", "block");
                    preForm.hide();
                    submitAll.hide();
                    break;
                case 2:
                    state.form = form2;
                    form2.form.css("display", "block");
                    form1.form.css("display", "none");
                    form3.form.css("display", "none");
                    nextForm.css("display", "block");
                    preForm.css("display", "block");
                    submitAll.css("display", "none");
                    break;
                case 3:
                    state.form = form3;
                    form3.form.css("display", "block");
                    form2.form.css("display", "none");
                    form1.form.css("display", "none");
                    nextForm.css("display", "none");
                    preForm.css("display", "block");
                    submitAll.css("display", "block");
                    break;
            }
        }
        changeState(1)
        nextForm.click(e => {
            console.log(state.form)
            if (state.form.isValid()) {
                console.log(state.form);
                changeState(++state.index)
            }else{
                console.log("Invalid");
            }

        })
        preForm.click(e => {
            changeState(--state.index)
        })
        submitAll.click(e => {
            try {
                var obj={};
                var temp = [...form1.formData().entries(),...form2.formData().entries(), ...form3.formData().entries()];
                temp.forEach(value => {
                    Object.assign(obj,{[value[0]]:value[1]});
                })
                $.get("./register", obj, (result) => {
                    console.log(result);
                })
            } catch {
                sweetAlert("Oops...", "Something went wrong !!", "error");
            }

        })
    })
</script>
</body>

</html>