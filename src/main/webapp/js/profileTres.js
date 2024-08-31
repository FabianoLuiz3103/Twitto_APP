$(document).ready(function() {
    var coracaoIcon = $("#coracao");
    coracaoIcon.click(function() {
        if (coracaoIcon.hasClass("far")) {
            coracaoIcon.removeClass("far").addClass("fas");
        } else {
            coracaoIcon.removeClass("fas").addClass("far");
        }
    });
});