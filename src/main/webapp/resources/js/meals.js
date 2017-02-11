var ajaxPath = "ajax/meals/", table, modalFilter, formFilter;

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

    var pattern = "YYYY-MM-DDTHH:mm";

    $("#dateTime").datetimepicker({
        format: pattern,
        locale: code,
        showClose: true
    });

    var from = $("#from");

    from.datetimepicker({
        format: pattern,
        locale: code,
        showClose: true
    });

    var to = $("#to");

    to.datetimepicker({
        format: pattern,
        locale: code,
        showClose: true,
        useCurrent: false
    });

    from.on("dp.change", function (e) {
        to.data("DateTimePicker").minDate(e.date);
    });

    to.on("dp.change", function (e) {
        from.data("DateTimePicker").maxDate(e.date);
    });

    modalFilter = $("#modalWindowFilter");
    formFilter  = $("#formInModalWindowFilter");
});

function updateTable() {
    $.get(ajaxPath, updateTableData);
}

function add() {
    titleModalSave.html(i18n["new"]);
    formSave.find(":input").val("");
    formSave.find($("#version")).val(0);
    modalSave.modal();
}

function update(id) {
    titleModalSave.html(i18n["editing"]);
    $.get(ajaxPath + id, function (data) {
        $.each(data, function (key, value) {
            if (key === "dateTime") {
                formSave.find($("#dateTime")).val(value.substring(0, 16));
                return true;
            }
            formSave.find("[name='" + key + "']").val(value);
        });
    });
    modalSave.modal();
}

function filter() {
    modalFilter.modal();
}

function between() {
    $.ajax({
        url: ajaxPath + "between",
        type: "POST",
        data: formFilter.serialize(),
        success: function (data) {
            modalFilter.modal("hide");
            updateTableData(data);
            successNoty("filtered", 500);
        }
    });
}

function discard() {
    formFilter[0].reset();
    updateTable();
    successNoty("reset", 500);
}