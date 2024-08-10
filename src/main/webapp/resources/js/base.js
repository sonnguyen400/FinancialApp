$("div.select-custom").each((idx, element_) => {
    var selectContainer = $(element_);
    selectContainer.html("<div class='content'></div>" + element_.innerHTML);
    selectContainer.append(`<select style="display:none" name=${selectContainer.attr("data-name")}></select>`);
    $(".content", selectContainer)
        .html($(".select .option[data-selected]", selectContainer)[0]?.outerHTML || $(".select .option:first-child", selectContainer)[0]?.outerHTML)
        .on("click", function () {
            $(".select", selectContainer).toggleClass("d-none")
        });
    $(".select .option", selectContainer).each((idx, option_) => {
        $("select", selectContainer).append(`<option data-index=${idx} ${option_.getAttribute("data-selected") && "selected"} value='${option_.getAttribute("data-value") || option_.innerHTML}'></option>`)
        $(option_).attr("data-index", idx).on("click", function (e) {
            $("select option[selected]", selectContainer).attr("selected", null);
            $(`select option[data-index=${idx}]`, selectContainer).attr("selected", true);
            $(".content", selectContainer).html(option_.outerHTML);
            $('.select', selectContainer).toggleClass("d-none")
        });

    })

})