<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"    uri="http://www.springframework.org/security/tags" %>

<spring:message code="jsp.meals"         var="i18n_meals"/>
<spring:message code="jsp.signOut"       var="i18n_signOut"/>
<spring:message code="jsp.users"         var="i18n_users"/>
<spring:message code="jsp.users.profile" var="i18n_users_profile"/>

<nav class="navbar navbar-fixed-top navbar-inverse">
    <div class="container">
        <div class="collapse navbar-collapse">
            <jsp:include page="lang.jsp"/>
            <form:form action="logout" class="navbar-form navbar-right" method="post">
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a class="btn btn-primary" href="meals">${i18n_meals}</a>
                        <a class="btn btn-primary" href="users">${i18n_users}</a>
                    </sec:authorize>
                    <a class="btn btn-primary" onclick="profile()">${i18n_users_profile}</a>
                    <button class="btn btn-success" type="submit">${i18n_signOut}</button>
                </sec:authorize>
            </form:form>
        </div>
    </div>
</nav>