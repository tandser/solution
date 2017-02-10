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
        "createdRow": function (row, data, dataIndex) {
            $(row).addClass(data.excess ? "danger" : "success");
        },
        "initComplete": makeEditable
    }));
});

$("#dateTime").datetimepicker({
    format: "YYYY-MM-DDTHH:mm",
    locale: code
});

$("#from").datetimepicker({
    format: "YYYY-MM-DDTHH:mm",
    locale: code
});

$("#to").datetimepicker({
    format: "YYYY-MM-DDTHH:mm",
    locale: code,
    useCurrent: false
});

$("#from").on("dp.change", function (e) {
    $('#to').data("DateTimePicker").minDate(e.date);
});

$("#to").on("dp.change", function (e) {
    $('#from').data("DateTimePicker").maxDate(e.date);
});

function updateTable() {
    $.get(ajaxPath, updateTableData);
}

function filter() {
    $("#modalWindowFilter").modal();
}

function between() {
    $.ajax({
        url: ajaxPath + "between",
        type: "POST",
        data: $("#formInModalWindowFilter").serialize(),
        success: function (data) {
            $("#modalWindowFilter").modal("hide");
            updateTableData(data);
            successNoty("filtered", 500);
        }
    });
}

function discard() {
    $("#from").data("DateTimePicker").clear();
    $("#to").data("DateTimePicker").clear();
    updateTable();
    successNoty("reset", 500);
}