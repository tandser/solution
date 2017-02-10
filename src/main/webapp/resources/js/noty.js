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