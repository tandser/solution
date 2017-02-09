var formSave;

function makeEditable() {
    formSave = $("#formInModalWindowSave");

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        errorNoty(event, jqXHR, options, jsExc)
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

function add() {
    $("#titleModalWindowSave").html(i18n["new"]);
    formSave.find(":input").val("");
    formSave.find($("#version")).val(0);
    $("#modalWindowSave").modal();
}

function update(id) {
    $("#titleModalWindowSave").html(i18n["editing"]);
    $.get(ajaxPath + id, function (data) {
        $.each(data, function (key, value) {
            formSave.find("[name='" + key + "']").val(
                key === "dateTime" ? value.substring(0, 16) : value
            );
        });
        formSave.find(".selectpicker").selectpicker("refresh");
        $("#modalWindowSave").modal();
    });
}

function save() {
    $.ajax({
        url: ajaxPath,
        type: "POST",
        data: formSave.serialize(),
        success: function () {
            $("#modalWindowSave").modal("hide");
            updateTable();
            successNoty("saved");
        }
    });
}

function remove(id) {
    $.ajax({
        url: ajaxPath + id,
        type: "DELETE",
        success: function () {
            updateTable();
            successNoty("removed");
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

function successNoty(key) {
    $.noty.closeAll();
    noty({
        theme: "relax",
        type: "success",
        layout: "bottomRight",
        timeout: 500,
        text: i18n[key]
    });
}

function errorNoty(event, jqXHR, options, jsExc) {
    $.noty.closeAll();
    noty({
        theme: "relax",
        type: "error",
        layout: "bottomRight",
        timeout: 1000,
        text: i18n["error"] + ": " + jqXHR.statusText
    });
}