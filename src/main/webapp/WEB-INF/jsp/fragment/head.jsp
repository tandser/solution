<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="jsp.diaryCalories" var="i18n_diaryCalories"/>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>${i18n_diaryCalories}</title>
<base href="${pageContext.request.contextPath}/"/>
<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="webjars/bootstrap-select/1.12.0/css/bootstrap-select.min.css"/>
<link rel="stylesheet" type="text/css" href="webjars/datatables/1.10.13/css/jquery.dataTables.min.css"/>
<link rel="stylesheet" type="text/css" href="webjars/datatables/1.10.13/css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="webjars/datetimepicker/2.5.4/build/jquery.datetimepicker.min.css"/>