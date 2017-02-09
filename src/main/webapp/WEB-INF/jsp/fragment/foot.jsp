<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="jsp.language" var="i18n_language"/>

<script type="text/javascript" src="webjars/jquery/3.1.1-1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap-select/1.12.0/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.13/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.13/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="webjars/moment/2.17.1/min/moment-with-locales.min.js"></script>
<script type="text/javascript" src="webjars/eonasdan-bootstrap-datetimepicker/4.17.45/build/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">
    var code = "${i18n_language}";
    function show(lang) {
        window.location.href = window.location.href.split("?")[0] + "?lang=" + lang;
    }
</script>