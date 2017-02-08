<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="jsp.access"         var="i18n_access"/>
<spring:message code="jsp.admin"          var="i18n_admin"/>
<spring:message code="jsp.close"          var="i18n_close"/>
<spring:message code="jsp.created"        var="i18n_created"/>
<spring:message code="jsp.editProfile"    var="i18n_editProfile"/>
<spring:message code="jsp.email"          var="i18n_email"/>
<spring:message code="jsp.name"           var="i18n_name"/>
<spring:message code="jsp.normOfCalories" var="i18n_normOfCalories"/>
<spring:message code="jsp.password"       var="i18n_password"/>
<spring:message code="jsp.rights"         var="i18n_rights"/>
<spring:message code="jsp.save"           var="i18n_save"/>
<spring:message code="jsp.user"           var="i18n_user"/>

<html>
    <head>
        <jsp:include page="fragment/head.jsp"/>
        <script defer type="text/javascript" src="resources/js/datatables/users.js"></script>
        <script defer type="text/javascript" src="resources/js/main.js"></script>
    </head>
    <body>
        <jsp:include page="fragment/header.jsp"/>
        <div class="jumbotron">
            <div class="container">
                <table class="table" id="datatable">
                    <thead>
                        <tr>
                            <th>${i18n_name}</th>
                            <th>${i18n_email}</th>
                            <th>${i18n_created}</th>
                            <th>${i18n_rights}</th>
                            <th>${i18n_access}</th>
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
                        <h2 class="modal-title">${i18n_editProfile}</h2>
                    </div>
                    <form class="form-horizontal" id="formInModalWindow" method="post">
                        <div class="modal-body">
                            <input id="id" name="id" type="hidden"/>
                            <input id="enabled" name="enabled" type="hidden"/>
                            <input id="version" name="version" type="hidden"/>
                            <div class="form-group">
                                <label class="control-label col-xs-3" for="name">${i18n_name}</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="name" name="name" type="text"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3" for="email">${i18n_email}</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="email" name="email" type="email"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3" for="password">${i18n_password}</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="password" name="password" type="password"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3" for="role">${i18n_rights}</label>
                                <div class="col-xs-9">
                                    <select class="form-control selectpicker" id="role" name="role">
                                        <option value="USER" selected>${i18n_user}</option>
                                        <option value="ADMIN">${i18n_admin}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-xs-3" for="normOfCalories">${i18n_normOfCalories}</label>
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
    </body>
</html>