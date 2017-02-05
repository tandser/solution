<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="jsp.login"           var="i18n_login"/>
<spring:message code="jsp.password"        var="i18n_password"/>
<spring:message code="jsp.signIn"          var="i18n_signIn"/>
<spring:message code="jsp.createAnAccount" var="i18n_createAnAccount"/>

<html>
    <head>
        <jsp:include page="fragment/head.jsp"/>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-collapse collapse">
                    <jsp:include page="fragment/lang.jsp"/>
                    <form class="navbar-form navbar-right" action="spring_security_check" method="post">
                        <div class="form-group">
                            <input type="text" placeholder="${i18n_login}" class="form-control" name="username"/>
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="${i18n_password}" class="form-control" name="password">
                        </div>
                        <button type="submit" class="btn btn-success">${i18n_signIn}</button>
                    </form>
                </div>
            </div>
        </nav>
        <div class="jumbotron">
            <div class="container">
                <h1>Hello, world!</h1>
                <p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
                <p><a class="btn btn-primary btn-lg" href="#">${i18n_createAnAccount}</a></p>
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
</html>
