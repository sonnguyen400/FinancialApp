function getParent(element,parent_selector){
    let parent=element.parentElement;
    while(!parent.classList.contains(parent_selector)){
        parent=parent.parentElement;
    }
    return parent;
}
const formulas = {
    email: function (e) {
        return (message = 'Email is invalid') => {
            let result = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(e.element.val());
            return result||message;
        };
    },
    exact: function (e) {
        return (reflect, message = 'Invalid value') => {
            return (e.element.val() === reflect)||message;
        };
    },
    required: function (e) {
        return (message = 'This field could not be blank') => {
            return  (e.element.val().trim()!== '')||message;
        };
    },
    min: (e) => {
        return (min, message = `Min value is min`) => {
            return (e.element.val() >= min)||message;
        };
    },
    max:e=>
            (max, message = `Max value is max`) => {
                return (e.element.val() <= max)||message;
            },
    agemin:e=>(age,message="age invalid")=>{
        let result=(new Date().getFullYear()-new Date(e.element.val()).getFullYear()) >= Number.parseInt(age);
        return result||message;
    },
    minlength: (e) => {
        return (min, message = `Min length is ${min}`) => {
            return (e.element.val().length >= min)||message;
        };
    },

};



function checkRule(element_rule,form){
    let result=true;
    for(let rule of element_rule.rules ){
        let formula=formulas[rule.name];
        if(formula){
            result=formula(element_rule)(rule.value);
            if(result!==true) break;
        }
    }
    
    let errContainer=$(".invalid-feedback",element_rule.element.prevObject)
    if(result!==true){
        errContainer.show();
        errContainer.html(result);
        return result;
    }else{
        errContainer.hide()
        return true;
    }
}
function partValidate(part){
    function getRule(raw_string){
        return raw_string.split("|").map(data=>{
            let rule_value=data.split(/[()]/);
            return {
                name:rule_value[0],
                value:rule_value[1]
            }
        })
    }
    if(part.length>=1){
        let element_rules=part.children().map((index,formGroup)=>{
            let formControl=$(".form-control",formGroup);
            let data_rule=formControl.attr("data-rule");
            if(!data_rule) return undefined;
            let rules=getRule(formControl.attr("data-rule"));
            return {
                element:formControl,
                rules:rules
            }
        })
        element_rules.each((index,element_rule)=>{
            element_rule.element.on("blur",function(e){
                checkRule(element_rule,part);
            })
        })

        return {
            isValid(){
                let status=true;
                element_rules.each((index,element_rule)=>{
                    if (checkRule(element_rule,part)!==true) status=false;
                })
                return status;
            },part
        }
    }
    
}
function stepFormValidate(form,onSubmit,start=0){
    let step=start;
    let parts=$(".part",form).map((index,part)=>partValidate($(part)));
    let nextBtn=$("#nextStep",form);
    let preBtn=$("#preStep",form);
    let submitBtn=$("#submitBtn",form);
    for(let i=0;i<parts.length;i++){
        if(i!==step){
            parts[i].part.addClass("d-none")
        }
    }

    function buttonStateRender(){
        if(step===0){
            preBtn.addClass("d-none");
            nextBtn.removeClass("d-none");
            if(parts.length>1){
                submitBtn.addClass("d-none");
            }
        }else if(step===(parts.length-1)){
            preBtn.removeClass("d-none");
            nextBtn.addClass("d-none");
            submitBtn.removeClass("d-none")
        }else{
            preBtn.removeClass("d-none");
            nextBtn.removeClass("d-none");
            submitBtn.addClass("d-none");
        }

    }
    buttonStateRender()

    nextBtn.on("click",function(){
        if(step<parts.length-1&&parts[step].isValid()){
            parts[step].part.addClass("d-none");
            step++;
            parts[step].part.removeClass("d-none");
            buttonStateRender();
        }
    })
    preBtn.on("click",function(){
        if(step>0){
            parts[step].part.addClass("d-none");
            step--;
            parts[step].part.removeClass("d-none");
            buttonStateRender();
        }
    })
    submitBtn.on("click",function(){
        let isValid=true;
        parts.each((index,part)=>{
            if(part.isValid()===false) isValid=false;
        })
        if(isValid){
            form.submit()
        }
    })
    
}