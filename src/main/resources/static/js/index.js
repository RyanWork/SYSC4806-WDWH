$(function () {
    $(".card").click(function () {
        // remove accent colours from all
        $(".card").removeClass("border-primary");
        $(".card-header").removeClass("bg-primary");

        // reset export button color
        $("#exportBtn").addClass("btn-info");
        $("#exportBtn").removeClass("btn-light");

        // add accent colors to clicked card only
        $(this).addClass("border-primary");
        $(this).find(".card-header").addClass("bg-primary");

        // if results clicked, change export color
        $(this).find("#exportBtn").removeClass("btn-info");
        $(this).find("#exportBtn").addClass("btn-light");

    });
});