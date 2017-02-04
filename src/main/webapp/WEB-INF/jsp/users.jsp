<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
    <jsp:include page="fragment/head.jsp"/>
    <script defer type="text/javascript" src="resources/js/datatables.util.js"></script>
    <script defer type="text/javascript" src="resources/js/datatables.user.js"></script>
    <body>
        <jsp:include page="fragment/header.jsp"/>

        <div class="jumbotron">
            <div class="container">

                <a class="btn btn-primary" onclick="add()"><spring:message code="jsp.addUser"/></a>

                <table class="table" id="datatable">
                    <thead>
                    <tr>
                        <th><spring:message code="jsp.name"/></th>
                        <th><spring:message code="jsp.email"/></th>
                        <th><spring:message code="jsp.rights"/></th>
                        <th><spring:message code="jsp.active"/></th>
                        <th><spring:message code="jsp.created"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>



                </table>
            </div>
        </div>

        <jsp:include page="fragment/footer.jsp"/>

        <div class="modal fade" id="editRow">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h2 class="modal-title"><spring:message code="jsp.createAnAccount"/></h2>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" method="post" id="detailsForm">

                            <%-- попробовать <input type="hidden" id="id" name="id"/> --%>

                            <input type="text" hidden="hidden" id="id" name="id"/>
                            <div class="form-group">
                                <label for="name" class="control-label col-xs-3"><spring:message code="jsp.name"/></label>
                                <div class="col-xs-9">
                                    <input type="text" class="form-control" id="name" name="name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="control-label col-xs-3"><spring:message code="jsp.email"/></label>
                                <div class="col-xs-9">
                                    <input type="text" class="form-control" id="email" name="email"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="control-label col-xs-3"><spring:message code="jsp.password"/></label>
                                <div class="col-xs-9">
                                    <input type="password" class="form-control" id="password" name="password"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-offset-3 col-xs-9">
                                    <button type="submit" class="btn btn-primary"><spring:message code="jsp.save"/></button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="jsp.close"/></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>