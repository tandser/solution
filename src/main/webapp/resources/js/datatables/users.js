var ajaxPath = "ajax/users/";
var table;

$(function () {
    table = $("#datatable").DataTable({
        "ajax": {
                "url": ajaxPath,
                "dataSrc": ""
        },
        "paging": false,
        "info": true,
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
                        return data.substring(0, 16);
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
                "defaultContent": "Edit",
                "orderable": false
            }, {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [[ 0, "asc" ]],
        "createdRow": function(row, data, dataIndex) {
            if (!data.enabled) {
                $(row).css("opacity", 0.5);
            }
        },
        "initComplete": makeEditable
    });
});