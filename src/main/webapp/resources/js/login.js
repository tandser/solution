var modalRegistration = $("#modalWindowRegistration"),
    formRegistration  = $("#formInModalWindowRegistration");

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