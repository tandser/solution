var form;

function makeEditable() {
    form = $("#formInModalWindow");
}

function append(opts) {
    $.extend(true, opts, {
        "ajax": {
            "url": ajaxPath,
            "dataSrc": ""
        },
        "paging": false,
        "info": true
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

function update(id) {
    $.get(ajaxPath + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $("#modalWindow").modal();
    });
}

function remove(id) {
    $.ajax({
        url: ajaxPath + id,
        type: "DELETE",
        success: function () {
            updateTable();
        }
    });
}

function updateTableData(data) {
    table.clear().rows.add(data).draw();
}

function updateButton(data, type, row) {
    if (type == "display") {
        return "<a class=\"btn btn-primary btn-xs\" onclick=\"update(" + row.id + ")\">" + "Edit" + "</a>";
    }
}

function removeButton(data, type, row) {
    if (type == "display") {
        return "<a class=\"btn btn-danger btn-xs\" onclick=\"remove(" + row.id + ")\">" + "Remove" + "</a>";
    }
}