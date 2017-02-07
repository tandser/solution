function makeEditable() {

}

function toggle(checkbox, id) {
    var state = checkbox.is(":checked");
    $.ajax({
        url: ajaxPath + "toggle/" + id,
        type: "POST",
        data: "state=" + state,
        success: function () {
            checkbox.closest("tr").fadeTo(500, state ? 1 : 0.5);
        }
    });
}