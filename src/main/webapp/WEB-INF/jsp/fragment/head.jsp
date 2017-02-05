<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="jsp.diaryCalories" var="i18n_diaryCalories"/>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${i18n_diaryCalories}</title>
        <base href="${pageContext.request.contextPath}/"/>
        <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
        <link rel="stylesheet" href="webjars/datatables/1.10.13/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="webjars/datatables/1.10.13/css/dataTables.bootstrap.min.css">
        <script defer type="text/javascript" src="webjars/jquery/3.1.1-1/jquery.min.js"></script>
        <script defer type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
        <script defer type="text/javascript" src="webjars/datatables/1.10.13/js/jquery.dataTables.min.js"></script>
        <script defer type="text/javascript" src="webjars/datatables/1.10.13/js/dataTables.bootstrap.min.js"></script>
        <script defer type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
        <script type="text/javascript">
            function show(lang) {
                window.location.href = window.location.href.split("?")[0] + "?lang=" + lang;
            }
        </script>