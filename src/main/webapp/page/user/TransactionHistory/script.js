$(function(){
    console.log("djhdj");
    var currentDate=new Date();
    var last7Day=new Date(currentDate.getTime()-7*24*60*60*1000);
    function renderTime(time){
        if(time<10) return `0${time}`;
        return time;
    }
    $("#startDate").val(`${last7Day.getFullYear()}-${renderTime(last7Day.getMonth()+1)}-${renderTime(last7Day.getDate())}`);
    $("#endDate").val(`${currentDate.getFullYear()}-${renderTime(currentDate.getMonth()+1)}-${renderTime(currentDate.getDate())}`);

})