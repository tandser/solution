var ajaxPath = "ajax/users/";
var table;

$(function () {
    table = $("#datatable").DataTable(append({
        "columns": [{
                "data": "name"
            }, {
                "data": "email",
                "render": function (data, type, row) {
                    if (type == "display") {
                        return "<a href=\"mailto:" + data + "\">" + data + "</a>";
                    }
                    return data;
                }
            }, {
                "data": "created",
                "render": function (data, type, row) {
                    if (type == "display") {
                        return data.replace("T", " ").substring(0, 16);
                    }
                    return data;
                }
            }, {
                "data": "role"
            }, {
                "data": "enabled",
                "render": function (data, type, row) {
                    if (type == "display") {
                        return "<input type=\"checkbox\" onclick=\"toggle($(this), " + row.id + ")\"" + (data ? " checked" : "") + "/>";
                    }
                    return data;
                }
            }, {
                "orderable": false,
                "defaultContent": "",
                "render": updateButton

            }, {
                "orderable": false,
                "defaultContent": "",
                "render": removeButton
            }
        ],
        "order": [[ 0, "asc" ]],
        "createdRow": function(row, data, dataIndex) {
            if (!data.enabled) {
                $(row).css("opacity", 0.5);
            }
        },
        "initComplete": makeEditable
    }));
});

function updateTable() {
    $.get(ajaxPath, updateTableData);
}

function toggle(checkbox, id) {
    var state = checkbox.is(":checked");
    $.ajax({
        url: ajaxPath + "toggle/" + id,
        type: "POST",
        data: "state=" + state,
        success: function () {
            checkbox.closest("tr").fadeTo(500, state ? 1 : 0.5);
            successNoty(state ? "enabled" : "disabled", 500);
        }
    });
}