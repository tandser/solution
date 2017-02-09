<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="jsp.edit"                      var="i18n_edit"/>
<spring:message code="jsp.error"                     var="i18n_error"/>
<spring:message code="jsp.remove"                    var="i18n_remove"/>
<spring:message code="jsp.save"                      var="i18n_save"/>
<spring:message code="jsp.datatables.loadingRecords" var="i18n_datatables_loadingRecords"/>
<spring:message code="jsp.datatables.search"         var="i18n_datatables_search"/>
<spring:message code="jsp.datatables.zeroRecords"    var="i18n_datatables_zeroRecords"/>
<spring:message code="jsp.meals.list"                var="i18n_meals_list"/>
<spring:message code="jsp.meals.dateTime"            var="i18n_meals_dateTime"/>
<spring:message code="jsp.meals.description"         var="i18n_meals_description"/>
<spring:message code="jsp.meals.calories"            var="i18n_meals_calories"/>
<spring:message code="jsp.save"                      var="i18n_save"/>
<spring:message code="jsp.close"                     var="i18n_close"/>
<spring:message code="jsp.meals.edit"                var="i18n_meals_edit"/>
<spring:message code="jsp.meals.new"                 var="i18n_meals_new"/>
<spring:message code="jsp.meals.removed"             var="i18n_meals_removed"/>
<spring:message code="jsp.meals.saved"               var="i18n_meals_saved"/>

<html>
    <head>
        <jsp:include page="fragment/head.jsp"/>
    </head>
    <body>
        <jsp:include page="fragment/header.jsp"/>
        <div class="jumbotron">
            <div class="container">
                <h2>${i18n_meals_list}</h2>
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
        <div class="modal fade" id="modalWindow">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2 class="modal-title"></h2>
                    </div>
                    <form class="form-horizontal" id="formInModalWindow" method="post">
                        <div class="modal-body">
                            <input id="id" name="id" type="hidden"/>
                            <input id="version" name="version" type="hidden"/>
                            <div class="form-group">
                                <label class="control-label col-xs-3" for="dateTime">${i18n_meals_dateTime}</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="dateTime" name="dateTime" type="text"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3" for="description">${i18n_meals_description}</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="description" name="description" type="text"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3" for="calories">${i18n_meals_calories}</label>
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
    </body>
    <jsp:include page="fragment/foot.jsp"/>
    <script type="text/javascript">
        var i18n = [];
        i18n["datatables_loadingRecords"] = "${i18n_datatables_loadingRecords}";
        i18n["datatables_search"]         = "${i18n_datatables_search}";
        i18n["datatables_zeroRecords"]    = "${i18n_datatables_zeroRecords}";
        i18n["edit"]                      = "${i18n_edit}";
        i18n["error"]                     = "${i18n_error}";
        i18n["remove"]                    = "${i18n_remove}";
        i18n["removed"]                   = "${i18n_meals_removed}";
        i18n["editing"]                   = "${i18n_meals_edit}";
        i18n["new"]                       = "${i18n_meals_new}";
        i18n["saved"]                     = "${i18n_meals_saved}";
    </script>
    <script type="text/javascript" src="resources/js/datatables/meals.js"></script>
    <script type="text/javascript" src="resources/js/main.js"></script>
</html>