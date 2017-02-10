<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="jsp.close"                     var="i18n_close"/>
<spring:message code="jsp.datatables.loadingRecords" var="i18n_datatables_loadingRecords"/>
<spring:message code="jsp.datatables.search"         var="i18n_datatables_search"/>
<spring:message code="jsp.datatables.zeroRecords"    var="i18n_datatables_zeroRecords"/>
<spring:message code="jsp.edit"                      var="i18n_edit"/>
<spring:message code="jsp.error"                     var="i18n_error"/>
<spring:message code="jsp.meals.add"                 var="i18n_meals_add"/>
<spring:message code="jsp.meals.apply"               var="i18n_meals_apply"/>
<spring:message code="jsp.meals.calories"            var="i18n_meals_calories"/>
<spring:message code="jsp.meals.dateTime"            var="i18n_meals_dateTime"/>
<spring:message code="jsp.meals.description"         var="i18n_meals_description"/>
<spring:message code="jsp.meals.discard"             var="i18n_meals_discard"/>
<spring:message code="jsp.meals.edit"                var="i18n_meals_edit"/>
<spring:message code="jsp.meals.filter"              var="i18n_meals_filter"/>
<spring:message code="jsp.meals.filtered"            var="i18n_meals_filtered"/>
<spring:message code="jsp.meals.filtering"           var="i18n_meals_filtering"/>
<spring:message code="jsp.meals.from"                var="i18n_meals_from"/>
<spring:message code="jsp.meals.list"                var="i18n_meals_list"/>
<spring:message code="jsp.meals.new"                 var="i18n_meals_new"/>
<spring:message code="jsp.meals.removed"             var="i18n_meals_removed"/>
<spring:message code="jsp.meals.reset"               var="i18n_meals_reset"/>
<spring:message code="jsp.meals.saved"               var="i18n_meals_saved"/>
<spring:message code="jsp.meals.to"                  var="i18n_meals_to"/>
<spring:message code="jsp.remove"                    var="i18n_remove"/>
<spring:message code="jsp.save"                      var="i18n_save"/>
<spring:message code="jsp.users.refreshed"           var="i18n_users_refreshed"/>

<html>
    <head>
        <jsp:include page="fragment/head.jsp"/>
    </head>
    <body>
        <jsp:include page="fragment/header.jsp"/>
        <div class="jumbotron">
            <div class="container">
                <h2>${i18n_meals_list}</h2>
                <a class="btn btn-primary" onclick="add()">${i18n_meals_add}</a>
                <a class="btn btn-primary" onclick="filter()">${i18n_meals_filter}</a>
                <table class="table" id="datatable">
                    <thead>
                        <tr>
                            <th>${i18n_meals_dateTime}</th>
                            <th>${i18n_meals_description}</th>
                            <th>${i18n_meals_calories}</th>
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
                            <input id="version" name="version" type="hidden"/>
                            <div class="form-group">
                                <label class="col-xs-3 control-label" for="dateTime">${i18n_meals_dateTime}</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="dateTime" name="dateTime" type="text"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label" for="description">${i18n_meals_description}</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="description" name="description" type="text"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label" for="calories">${i18n_meals_calories}</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="calories" name="calories" type="number"/>
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
        <div class="fade modal" id="modalWindowFilter">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2 class="modal-title">${i18n_meals_filtering}</h2>
                    </div>
                    <form class="form-horizontal" id="formInModalWindowFilter">
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="col-xs-3 control-label" for="from">${i18n_meals_from}</label>
                                <div class="col-xs-9">
                                    <input class="date form-control input-group" id="from" name="from" type="text"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label" for="to">${i18n_meals_to}</label>
                                <div class="col-xs-9">
                                    <input class="date form-control input-group" id="to" name="to" type="text"/>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-default" data-dismiss="modal" type="button">${i18n_close}</button>
                            <button class="btn btn-default" onclick="discard()" type="button">${i18n_meals_discard}</button>
                            <button class="btn btn-primary" onclick="between()" type="button">${i18n_meals_apply}</button>
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
        i18n["edit"]           = "${i18n_edit}";
        i18n["editing"]        = "${i18n_meals_edit}";
        i18n["error"]          = "${i18n_error}";
        i18n["filtered"]       = "${i18n_meals_filtered}";
        i18n["loadingRecords"] = "${i18n_datatables_loadingRecords}";
        i18n["new"]            = "${i18n_meals_new}";
        i18n["refreshed"]      = "${i18n_users_refreshed}";
        i18n["remove"]         = "${i18n_remove}";
        i18n["removed"]        = "${i18n_meals_removed}";
        i18n["reset"]          = "${i18n_meals_reset}";
        i18n["saved"]          = "${i18n_meals_saved}";
        i18n["search"]         = "${i18n_datatables_search}";
        i18n["zeroRecords"]    = "${i18n_datatables_zeroRecords}";
    </script>
    <script type="text/javascript" src="resources/js/datatables/meals.js"></script>
    <script type="text/javascript" src="resources/js/main.js"></script>
</html>