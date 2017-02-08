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

<html>
    <head>
        <jsp:include page="fragment/head.jsp"/>
        <script defer type="text/javascript" src="resources/js/datatables/meals.js"></script>
        <script defer type="text/javascript" src="resources/js/main.js"></script>
        <script type="text/javascript">
            var i18n = [];
            i18n["datatables_loadingRecords"] = "${i18n_datatables_loadingRecords}";
            i18n["datatables_search"]         = "${i18n_datatables_search}";
            i18n["datatables_zeroRecords"]    = "${i18n_datatables_zeroRecords}";
            i18n["edit"]                      = "${i18n_edit}";
            i18n["error"]                     = "${i18n_error}";
            i18n["remove"]                    = "${i18n_remove}";
        </script>
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
    </body>
</html>