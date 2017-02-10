<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="jsp.close"                var="i18n_close"/>
<spring:message code="jsp.save"                 var="i18n_save"/>
<spring:message code="jsp.users.edit"           var="i18n_users_edit"/>
<spring:message code="jsp.users.email"          var="i18n_users_email"/>
<spring:message code="jsp.users.name"           var="i18n_users_name"/>
<spring:message code="jsp.users.normOfCalories" var="i18n_users_normOfCalories"/>
<spring:message code="jsp.users.password"       var="i18n_users_password"/>

<div class="fade modal" id="modalWindowProfile">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title">${i18n_users_edit}</h2>
            </div>
            <form class="form-horizontal" id="formInModalWindowProfile">
                <div class="modal-body">
                    <input id="id" name="id" type="hidden"/>
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
                        <label class="col-xs-3 control-label" for="normOfCalories">${i18n_users_normOfCalories}</label>
                        <div class="col-xs-9">
                            <input class="form-control" id="normOfCalories" name="normOfCalories" type="number"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal" type="button">${i18n_close}</button>
                    <button class="btn btn-primary" onclick="refresh()" type="button">${i18n_save}</button>
                </div>
            </form>
        </div>
    </div>
</div>