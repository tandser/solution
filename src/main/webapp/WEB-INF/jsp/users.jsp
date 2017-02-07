<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="jsp.name"    var="i18n_name"/>
<spring:message code="jsp.email"   var="i18n_email"/>
<spring:message code="jsp.rights"  var="i18n_rights"/>
<spring:message code="jsp.created" var="i18n_created"/>
<spring:message code="jsp.access"  var="i18n_access"/>

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
    </body>
</html>