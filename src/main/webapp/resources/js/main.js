var form;

function makeEditable() {
    form = $("#formInModalWindow");

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
            "loadingRecords": i18n["datatables_loadingRecords"],
            "search": i18n["datatables_search"],
            "zeroRecords": i18n["datatables_zeroRecords"]
        }
    });
    return opts;
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

function add() {
    $(".modal-title").html(i18n["new"]);
    form.find(":input").val("");
    form.find($("#version")).val(0);
    $("#modalWindow").modal();
}

function update(id) {
    $(".modal-title").html(i18n["editing"]);
    $.get(ajaxPath + id, function (data) {
        $.each(data, function (key, value) {
            form.find("[name='" + key + "']").val(
                key === "dateTime" ? value.substring(0, 16) : value
            );
        });
        form.find(".selectpicker").selectpicker("refresh");
        $("#modalWindow").modal();
    });
}

function save() {
    $.ajax({
        url: ajaxPath,
        type: "POST",
        data: form.serialize(),
        success: function () {
            $("#modalWindow").modal("hide");
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