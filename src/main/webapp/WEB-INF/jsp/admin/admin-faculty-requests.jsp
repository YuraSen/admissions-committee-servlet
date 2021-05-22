<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../fragments/navbar.jsp"/>
<br>




<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h3 class="panel-title">Admission Requests: </h3>
                        </div>

                    </div>
                </div>
                <div class="panel-body">
                    <table class="table table-striped table-bordered table-list">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>From</th>
                            <th>To the Faculty</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <jsp:useBean id="faculty" type="entity.Faculty" scope="request"/>
                        <c:forEach var="req" items="${faculty.admissionRequestList}">

                            <tr>

                                <td>${req.id}</td>
                                <td>${req.applicant.applicantProfile.firstName} ${req.applicant.applicantProfile.lastName}</td>
                                <td>${faculty.name}</td>
                                <td>${req.admissionRequestStatus.name()}</td>
                                <td>
                                    <form
                                            action="/controller" method="post">
                                        <input type="hidden" name="command" value="checkRequestFromFacultyReqList">
                                        <input type="hidden" name="requestId" value="${req.id}">
                                        <button class="btn btn-primary" type="submit">Check request</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>


</body>


</body>
</html>
