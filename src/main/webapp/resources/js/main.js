var modalSave, titleModalSave, formSave, modalProfile, formProfile, ajaxProfile = "ajax/users/profile";

function makeEditable() {
    modalSave      = $("#modalWindowSave");
    titleModalSave = $("#titleModalWindowSave");
    formSave       = $("#formInModalWindowSave");
    modalProfile   = $("#modalWindowProfile");
    formProfile    = $("#formInModalWindowProfile");

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        errorNoty(event, jqXHR, options, jsExc, "error", 1000)
    });
}

function append(opts) {
    $.extend(true, opts, {
        "ajax": {
            "url": ajaxPath,
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
        "language": {
            "loadingRecords": i18n["loadingRecords"],
            "search": i18n["search"],
            "zeroRecords": i18n["zeroRecords"]
        }
    });
    return opts;
}

function save() {
    $.ajax({
        url: ajaxPath,
        type: "POST",
        data: formSave.serialize(),
        success: function () {
            modalSave.modal("hide");
            updateTable();
            successNoty("saved", 500);
        }
    });
}

function remove(id) {
    $.ajax({
        url: ajaxPath + id,
        type: "DELETE",
        success: function () {
            updateTable();
            successNoty("removed", 500);
        }
    });
}

function profile() {
    $.get(ajaxProfile, function (data) {
        $.each(data, function (key, value) {
            formProfile.find("[name='" + key + "']").val(value);
        });
    });
    formProfile.find($("#password")).val("");
    modalProfile.modal();
}

function refresh() {
    $.ajax({
        url: ajaxProfile,
        type: "POST",
        data: formProfile.serialize(),
        success: function () {
            modalProfile.modal("hide");
            updateTable();
            successNoty("refreshed", 500);
        }
    });
}

function updateTableData(data) {
    table.clear().rows.add(data).draw();
}

function updateButton(data, type, row) {
    if (type == "display") {
        return "<a class=\"btn btn-primary btn-xs\" onclick=\"update(" + row.id + ")\">" + i18n["edit"] + "</a>";
    }
}

function removeButton(data, type, row) {
    if (type == "display") {
        return "<a class=\"btn btn-danger btn-xs\" onclick=\"remove(" + row.id + ")\">" + i18n["remove"] + "</a>";
    }
}