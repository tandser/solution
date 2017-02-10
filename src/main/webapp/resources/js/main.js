var token  = $("meta[name='_csrf']").attr("content"),
    header = $("meta[name='_csrf_header']").attr("content");

$(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
});

$(document).ajaxError(function (event, jqXHR, options, jsExc) {
    errorNoty(event, jqXHR, options, jsExc, "error", 1000)
});

function successNoty(key, timeout) {
    $.noty.closeAll();
    noty({
        layout: "bottomRight",
        theme: "relax",
        type: "success",
        text: i18n[key],
        timeout: timeout
    });
}

function errorNoty(event, jqXHR, options, jsExc, key, timeout) {
    $.noty.closeAll();
    noty({
        layout: "bottomRight",
        theme: "relax",
        type: "error",
        text: i18n[key] + " - " + jqXHR.statusText,
        timeout: timeout
    });
}