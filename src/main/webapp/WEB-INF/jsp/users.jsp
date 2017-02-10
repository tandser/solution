<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="jsp.close"                     var="i18n_close"/>
<spring:message code="jsp.datatables.loadingRecords" var="i18n_datatables_loadingRecords"/>
<spring:message code="jsp.datatables.search"         var="i18n_datatables_search"/>
<spring:message code="jsp.datatables.zeroRecords"    var="i18n_datatables_zeroRecords"/>
<spring:message code="jsp.edit"                      var="i18n_edit"/>
<spring:message code="jsp.error"                     var="i18n_error"/>
<spring:message code="jsp.remove"                    var="i18n_remove"/>
<spring:message code="jsp.save"                      var="i18n_save"/>
<spring:message code="jsp.users.access"              var="i18n_users_access"/>
<spring:message code="jsp.users.add"                 var="i18n_users_add"/>
<spring:message code="jsp.users.admin"               var="i18n_users_admin"/>
<spring:message code="jsp.users.created"             var="i18n_users_created"/>
<spring:message code="jsp.users.disabled"            var="i18n_users_disabled"/>
<spring:message code="jsp.users.edit"                var="i18n_users_edit"/>
<spring:message code="jsp.users.email"               var="i18n_users_email"/>
<spring:message code="jsp.users.enabled"             var="i18n_users_enabled"/>
<spring:message code="jsp.users.list"                var="i18n_users_list"/>
<spring:message code="jsp.users.name"                var="i18n_users_name"/>
<spring:message code="jsp.users.new"                 var="i18n_users_new"/>
<spring:message code="jsp.users.normOfCalories"      var="i18n_users_normOfCalories"/>
<spring:message code="jsp.users.password"            var="i18n_users_password"/>
<spring:message code="jsp.users.refreshed"           var="i18n_users_refreshed"/>
<spring:message code="jsp.users.removed"             var="i18n_users_removed"/>
<spring:message code="jsp.users.rights"              var="i18n_users_rights"/>
<spring:message code="jsp.users.saved"               var="i18n_users_saved"/>
<spring:message code="jsp.users.user"                var="i18n_users_user"/>

<html>
    <head>
        <jsp:include page="fragment/head.jsp"/>
    </head>
    <body>
        <jsp:include page="fragment/header.jsp"/>
        <div class="jumbotron">
            <div class="container">
                <h2>${i18n_users_list}</h2>
                <a class="btn btn-primary" onclick="add()">${i18n_users_add}</a>
                <table class="table" id="datatable">
                    <thead>
                        <tr>
                            <th>${i18n_users_name}</th>
                            <th>${i18n_users_email}</th>
                            <th>${i18n_users_created}</th>
                            <th>${i18n_users_rights}</th>
                            <th>${i18n_users_access}</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>
        <div class="container">
            <jsp:include page="fragment/footer.jsp"/>
        </div>
        <div class="fade modal" id="modalWindowSave">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2 class="modal-title" id="titleModalWindowSave"></h2>
                    </div>
                    <form class="form-horizontal" id="formInModalWindowSave">
                        <div class="modal-body">
                            <input id="id" name="id" type="hidden"/>
                            <input id="enabled" name="enabled" type="hidden"/>
                            <input id="version" name="version" type="hidden"/>
                            <div class="form-group">
                                <label class="col-xs-3 control-label" for="name">${i18n_users_name}</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="name" name="name" type="text"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label" for="email">${i18n_users_email}</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="email" name="email" type="email"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label" for="password">${i18n_users_password}</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="password" name="password" type="password"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label" for="role">${i18n_users_rights}</label>
                                <div class="col-xs-9">
                                    <select class="form-control selectpicker show-menu-arrow" id="role" name="role">
                                        <option value="USER" selected>${i18n_users_user}</option>
                                        <option value="ADMIN">${i18n_users_admin}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label" for="normOfCalories">${i18n_users_normOfCalories}</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="normOfCalories" name="normOfCalories" type="number"/>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-default" data-dismiss="modal" type="button">${i18n_close}</button>
                            <button class="btn btn-primary" onclick="save()" type="button">${i18n_save}</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="fragment/profile.jsp"/>
    </body>
    <jsp:include page="fragment/foot.jsp"/>
    <script type="text/javascript">
        var i18n = [];
        i18n["disabled"]       = "${i18n_users_disabled}";
        i18n["edit"]           = "${i18n_edit}";
        i18n["editing"]        = "${i18n_users_edit}";
        i18n["enabled"]        = "${i18n_users_enabled}";
        i18n["error"]          = "${i18n_error}";
        i18n["loadingRecords"] = "${i18n_datatables_loadingRecords}";
        i18n["new"]            = "${i18n_users_new}";
        i18n["refreshed"]      = "${i18n_users_refreshed}";
        i18n["remove"]         = "${i18n_remove}";
        i18n["removed"]        = "${i18n_users_removed}";
        i18n["saved"]          = "${i18n_users_saved}";
        i18n["search"]         = "${i18n_datatables_search}";
        i18n["zeroRecords"]    = "${i18n_datatables_zeroRecords}";
    </script>
    <script type="text/javascript" src="resources/js/main.js"></script>
    <script type="text/javascript" src="resources/js/users.js"></script>
    <script type="text/javascript" src="resources/js/shared.js"></script>
</html>