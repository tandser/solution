var modalRegistration = $("#modalWindowRegistration"), formRegistration = $("#formInModalWindowRegistration");

$(document).ajaxError(function (event, jqXHR, options, jsExc) {
    errorNoty(event, jqXHR, options, jsExc, "error", 1000)
});

function registration() {
    formRegistration[0].reset();
    modalRegistration.modal();
}

function register() {
    $.ajax({
        url: "ajax/users/registration",
        type: "POST",
        data: formRegistration.serialize(),
        success: function () {
            modalRegistration.modal("hide");
            successNoty("gratitude", 10000);
        }
    });
}