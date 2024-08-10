<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    var coop=[];
    var searchResult=null;

    function addCoop(){
        console.log(searchResult)
        if(!coop.some(coop_=>coop_.id===searchResult.id)){
            coop.push(searchResult);
            searchResult=null;
            renderCoop();
            renderResult();
        }
    }
    function deleteCoop(id){
        for(let i=0;i<coop.length;i++){
            if(coop[i].id===id){
                coop.splice(i,1);
                renderCoop();
                return;
            }
        }
    }
    function submit(){
        $("#coop").submit();
    }
    function renderCoop(){
        $("#coop").html(coop.map(coop_=>`
                    <div class="col-3 position-relative">
                        <div class="d-flex align-items-center flex-column">
                            <div><img class="w-100" src="<c:url value="/resources/images/avatar/unknown.webp"/> "></div>
                            <small>\${coop_.firstname} \${coop_.lastname}</small>
                            <input type="hidden" name="coop" value="\${coop_.id}">
                            <div style="top:0px;left:0px" onClick="deleteCoop(\${coop_.id})" class="bg-primary-darken-5 position-absolute w-100 h-100 opacity-6 d-flex align-items-center justify-content-center">
                             Delete
                        </div>
                        </div>
                    </div>`).join("")+(coop.length>0?`<div class="col-12 pt-4 d-flex justify-content-end"><button class="btn btn-primary" onclick="submit()">Continue</button></div>`:""))
    }
    function renderResult(){
        if(searchResult){
            $("#searchResult").html(`<div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-row">
                            <div class="rounded-circle" style="flex: 1">
                                <img class="w-100" src="<c:url value="/resources/images/avatar/unknown.webp"/> ">
                            </div>
                            <div style="flex: 5" class="pt-4 pl-2">
                                <div class="card-title">\${searchResult.firstname} \${searchResult.lastname}</div>
                                <div class="card-subtitle">\${searchResult.email}</div>
                            </div>
                        </div>
                        <div class="my-2">
                            <div class="pb-2">\${searchResult.phone}</div>
                            <div class="pb-2">\${(new Date(searchResult.dateOfBirth)).toString()}</div>
                        </div>
                        <div class="d-flex justify-content-end">
                                <button class="btn btn-primary" onClick="addCoop()"><i class="fi fi-sr-plus-small"></i></button>
                            </div>
                    </div>
                </div>`)
        }else $("#searchResult").html("")

    }
$(function (){


    $("#searchBtn").on("click",function (){
        $.get(`/app/ajax/customer/email?value=\${$("#searchInp").val()}`,function (payload) {
            searchResult=payload;
            renderResult();
        }).catch(function (e){
            console.log(e);
        });
    });
})
</script>