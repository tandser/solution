<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:message code="jsp.close"                     var="i18n_close"/>
<spring:message code="jsp.error"                     var="i18n_error"/>
<spring:message code="jsp.gratitude"                 var="i18n_gratitude"/>
<spring:message code="jsp.readMore"                  var="i18n_readMore"/>
<spring:message code="jsp.save"                      var="i18n_save"/>
<spring:message code="jsp.signIn"                    var="i18n_signIn"/>
<spring:message code="jsp.title"                     var="i18n_title"/>
<spring:message code="jsp.users.create"              var="i18n_users_create"/>
<spring:message code="jsp.users.email"               var="i18n_users_email"/>
<spring:message code="jsp.users.name"                var="i18n_users_name"/>
<spring:message code="jsp.users.new"                 var="i18n_users_new"/>
<spring:message code="jsp.users.normOfCalories"      var="i18n_users_normOfCalories"/>
<spring:message code="jsp.users.password"            var="i18n_users_password"/>
<spring:message code="jsp.users.validNormOfCalories" var="i18n_users_validNormOfCalories"/>
<spring:message code="jsp.users.validPassword"       var="i18n_users_validPassword"/>
<spring:message code="jsp.welcome"                   var="i18n_welcome"/>

<spring:message code="jsp.recipe1"                   var="i18n_recipe1"/>
<spring:message code="jsp.recipe1.text"              var="i18n_recipe1_text"/>

<spring:message code="jsp.recipe2"                   var="i18n_recipe2"/>
<spring:message code="jsp.recipe2.text"              var="i18n_recipe2_text"/>

<spring:message code="jsp.recipe3"                   var="i18n_recipe3"/>
<spring:message code="jsp.recipe3.text"              var="i18n_recipe3_text"/>

<html>
    <head>
        <jsp:include page="fragment/head.jsp"/>
    </head>
    <body>
        <nav class="navbar navbar-fixed-top navbar-inverse">
            <div class="container">
                <jsp:include page="fragment/lang.jsp"/>
                <div class="collapse navbar-collapse">
                    <form:form action="spring_security_check" class="navbar-form navbar-right" method="post">
                        <div class="form-group">
                            <input class="form-control" name="username" placeholder="${i18n_users_email}" type="text"/>
                        </div>
                        <div class="form-group">
                            <input class="form-control" name="password" placeholder="${i18n_users_password}" type="password"/>
                        </div>
                        <button class="btn btn-success" type="submit">${i18n_signIn}</button>
                    </form:form>
                </div>
            </div>
        </nav>
        <div class="jumbotron">
            <div class="container">
                <h1>${i18n_title}</h1>
                <p class="text-justify">${i18n_welcome}</p>
                <p><a class="btn btn-lg btn-primary" onclick="registration()">${i18n_users_create}</a></p>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <h2>${i18n_recipe1}</h2>
                    <p>${i18n_recipe1_text}</p>
                    <p><a class="btn btn-default" href="http://www.finaeva.com/blog/%D1%82%D1%8B%D0%BA%D0%B2%D0%B5%D0%BD%D0%BD%D0%BE%D0%B5-%D0%BF%D0%B5%D1%87%D0%B5%D0%BD%D1%8C%D0%B5/">${i18n_readMore}</a></p>
                </div>
                <div class="col-md-4">
                    <h2>${i18n_recipe2}</h2>
                    <p>${i18n_recipe2_text}</p>
                    <p><a class="btn btn-default" href="http://www.finaeva.com/blog/%D0%B2%D0%B0%D0%BD%D0%B8%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9-%D0%BF%D0%B8%D1%80%D0%BE%D0%B3/">${i18n_readMore}</a></p>
                </div>
                <div class="col-md-4">
                    <h2>${i18n_recipe3}</h2>
                    <p>${i18n_recipe3_text}</p>
                    <p><a class="btn btn-default" href="http://www.finaeva.com/blog/%D0%B3%D1%80%D0%B0%D0%BD%D0%BE%D0%BB%D0%B0-%D1%81-%D1%81%D0%B5%D0%BC%D0%B5%D1%87%D0%BA%D0%B0%D0%BC%D0%B8/">${i18n_readMore}</a></p>
                </div>
            </div>
            <jsp:include page="fragment/footer.jsp"/>
        </div>
        <div class="fade modal" id="modalWindowRegistration">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2 class="modal-title">${i18n_users_new}</h2>
                    </div>
                    <form class="form-horizontal" id="formInModalWindowRegistration">
                        <div class="modal-body">
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
                                    <input class="form-control" id="password" name="password" placeholder="${i18n_users_validPassword}" type="password"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3 control-label" for="normOfCalories">${i18n_users_normOfCalories}</label>
                                <div class="col-xs-9">
                                    <input class="form-control" id="normOfCalories" name="normOfCalories" placeholder="${i18n_users_validNormOfCalories}" type="number"/>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-default" data-dismiss="modal" type="button">${i18n_close}</button>
                            <button class="btn btn-primary" onclick="register()" type="button">${i18n_save}</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="fragment/foot.jsp"/>
    <script type="text/javascript">
        var i18n = [];
        i18n["error"]     = "${i18n_error}";
        i18n["gratitude"] = "${i18n_gratitude}";
    </script>
    <script type="text/javascript" src="resources/js/main.js"></script>
    <script type="text/javascript" src="resources/js/login.js"></script>
</html>
