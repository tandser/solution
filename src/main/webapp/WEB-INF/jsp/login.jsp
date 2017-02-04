<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="jsp.diaryCalories"/></title>
        <base href="${pageContext.request.contextPath}/"/>
        <link href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" rel="stylesheet">
        <script type="text/javascript" src="webjars/jquery/3.1.1-1/jquery.min.js" defer></script>
        <script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js" defer></script>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#"><spring:message code="jsp.diaryCalories"/></a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <form class="navbar-form navbar-right" action="spring_security_check" method="post">
                        <div class="form-group">
                            <input type="text" placeholder="<spring:message code='jsp.login'/>" class="form-control" name="username">
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="<spring:message code='jsp.password'/>" class="form-control" name="password">
                        </div>
                        <button type="submit" class="btn btn-success"><spring:message code="jsp.signIn"/></button>
                    </form>
                </div>
            </div>
        </nav>
        <div class="jumbotron">
            <div class="container">
                <h1>Hello, world!</h1>
                <p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
                <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more &raquo;</a></p>
            </div>
        </div>
    </body>
</html>
