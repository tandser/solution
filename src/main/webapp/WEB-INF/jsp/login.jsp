<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="jsp.signIn"         var="i18n_signIn"/>
<spring:message code="jsp.users.create"   var="i18n_users_create"/>
<spring:message code="jsp.users.email"    var="i18n_users_email"/>
<spring:message code="jsp.users.password" var="i18n_users_password"/>

<html>
    <head>
        <jsp:include page="fragment/head.jsp"/>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-collapse collapse">
                    <jsp:include page="fragment/lang.jsp"/>
                    <form action="spring_security_check" class="navbar-form navbar-right" method="post">
                        <div class="form-group">
                            <input class="form-control" name="username" placeholder="${i18n_users_email}" type="text"/>
                        </div>
                        <div class="form-group">
                            <input class="form-control" name="password" placeholder="${i18n_users_password}" type="password"/>
                        </div>
                        <button class="btn btn-success" type="submit">${i18n_signIn}</button>
                    </form>
                </div>
            </div>
        </nav>
        <div class="jumbotron">
            <div class="container">
                <h1>Hello, world!</h1>
                <p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
                <p><a class="btn btn-primary btn-lg" href="#">${i18n_users_create}</a></p>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <h2>Heading</h2>
                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                    <p><a class="btn btn-default" href="#">View details &raquo;</a></p>
                </div>
                <div class="col-md-4">
                    <h2>Heading</h2>
                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                    <p><a class="btn btn-default" href="#">View details &raquo;</a></p>
                </div>
                <div class="col-md-4">
                    <h2>Heading</h2>
                    <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
                    <p><a class="btn btn-default" href="#">View details &raquo;</a></p>
                </div>
            </div>
            <jsp:include page="fragment/footer.jsp"/>
        </div>
    </body>
    <jsp:include page="fragment/foot.jsp"/>
</html>
