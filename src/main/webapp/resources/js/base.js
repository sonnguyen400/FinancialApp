var selectContainer = $(".select-custom");
selectContainer.html("<div class='content'></div>" + selectContainer.html())
selectContainer.append(`<select class="d-none" name="${$(".select", selectContainer).attr("data-name")}"></select>`)
$(".content", selectContainer).on("click", function () {
    $(".select", selectContainer).toggleClass("d-none")
});
$('.content', selectContainer).html($(".select .option[data-selected]", selectContainer)[0]?.outerHTML || $(".select .option:first-child", selectContainer)[0]?.outerHTML);
var options = $(".select .option", selectContainer);
var select = $("select", selectContainer)
options.each((index, option_) => {
    option_.setAttribute("data-key", index)
    select.append(`<option data-key=${index} ${option_.getAttribute("data-selected") !== null && "selected"}>${option_.getAttribute("data-value") || option_.innerHTML}</option>`)
    option_.addEventListener('click', function () {
        $("option[selected]", select).attr("selected", null);
        $(`option[data-key=${index}]`).attr("selected", true);
        $('.content', selectContainer).html(option_.outerHTML);
        $(".select", selectContainer).toggleClass("d-none");
    })
})