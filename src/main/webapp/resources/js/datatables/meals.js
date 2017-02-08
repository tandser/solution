var ajaxPath = "ajax/meals/";
var table;

$(function () {
    table = $("#datatable").DataTable(append({
        "columns": [{
                "data": "dateTime",
                "render": function (data, type, row) {
                    if (type == "display") {
                        return data.replace("T", " ").substring(0, 16);
                    }
                    return data;
                }
            }, {
                "data": "description"
            }, {
                "data": "calories"
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
        "order": [[ 0, "desc" ]],
        "initComplete": makeEditable
    }));
});

function updateTable() {
    $.get(ajaxPath, updateTableData);
}