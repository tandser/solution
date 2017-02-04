var restUrl = "rest/users/";
var datatableApi;

$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging"  : false,
        "info"    : true,
        "columns" : [
            { "data" : "name" },
            { "data" : "email" },
            { "data" : "role" },
            { "data" : "enabled" },
            { "data" : "created" },
            { "defaultContent" : "", "orderable" : false},
            { "defaultContent" : "", "orderable" : false}
        ],
        "order"   : [
            [ 0, "asc" ]
        ]
    });

})