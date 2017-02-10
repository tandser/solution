var formRegistration = $("#formInModalWindowRegistration");

$(document).ajaxError(function (event, jqXHR, options, jsExc) {
    errorNoty(event, jqXHR, options, jsExc, "error", 1000)
});

function registration() {
    formRegistration.find(":input").val("");
    $("#modalWindowRegistration").modal();
}

function register() {
    $.ajax({
        url: "ajax/users/registration",
        type: "POST",
        data: formRegistration.serialize(),
        success: function () {
            $("#modalWindowRegistration").modal("hide");
            successNoty("gratitude", 10000);
        }
    });
}