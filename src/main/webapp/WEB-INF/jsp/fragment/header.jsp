<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:message code="jsp.addUser" var="i18n_addUser"/>
<spring:message code="jsp.profile" var="i18n_profile"/>
<spring:message code="jsp.signOut" var="i18n_signOut"/>
<spring:message code="jsp.users"   var="i18n_users"/>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-collapse collapse">
            <jsp:include page="lang.jsp"/>
            <form class="navbar-form navbar-right" action="logout" method="post">
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a class="btn btn-primary" onclick="add()">${i18n_addUser}</a>
                        <a class="btn btn-primary" href="users">${i18n_users}</a>
                    </sec:authorize>
                    <a class="btn btn-primary" href="profile">${i18n_profile}</a>
                    <button type="submit" class="btn btn-success">${i18n_signOut}</button>
                </sec:authorize>
            </form>
        </div>
    </div>
</nav>