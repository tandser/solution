<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header navbar-brand">
            <spring:message code="jsp.diaryCalories"/>
        </div>
        <div class="navbar-collapse collapse">
            <form class="navbar-form navbar-right" action="logout" method="post">
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a class="btn btn-primary" onclick="add()"><spring:message code="jsp.addUser"/></a>
                        <a class="btn btn-primary" href="users"><spring:message code="jsp.users"/></a>
                    </sec:authorize>
                    <a class="btn btn-primary" href="profile"><spring:message code="jsp.profile"/></a>
                    <button type="submit" class="btn btn-success"><spring:message code="jsp.signOut"/></button>
                </sec:authorize>
            </form>
        </div>
    </div>
</nav>