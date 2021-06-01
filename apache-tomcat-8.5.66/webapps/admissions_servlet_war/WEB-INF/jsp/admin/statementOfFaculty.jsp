<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://admissions_servlet/functions" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>

<html lang="${sessionScope.lang}">
<c:set var="title" value="Faculties" scope="page"/>
<head>
    <title>Admin Workspace</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">

    <a class="navbar-brand" href="/controller?command=adminWorkspace"><fmt:message
            key="navbar.Admission_Board_App"/></a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav">

            <li class="nav-item dropdown my-2">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
                                            data-target="#navbarDropdown"><fmt:message key="navbar.Change_Language"/>
                        <b class="caret"></b></a>
                        <div class="dropdown-menu dropdown-menu-right" id="navbarDropdown">
                            <a class="dropdown-item"
                               href="/controller?command=getStatementOfFaculty&facultyId=${facultyId}&sessionLocale=en">
                                <fmt:message key="navbar.English"/></a>
                            <a class="dropdown-item"
                               href="/controller?command=getStatementOfFaculty&facultyId=${facultyId}&sessionLocale=uk">
                                <fmt:message key="navbar.Ukrainian"/></a>
                        </div>

                    </li>
                </ul>
            </li>
            <li class="nav-item ml-4 mr-2">
                <form class="form-inline ">
                    <a class="btn btn-primary my-2 my-sm-0" href="/controller?command=adminWorkspace" role="button">
                        <fmt:message key="navbar.Admin_workspace_faculty"/></a>
                </form>

            </li>
            <li class="nav-item mr-2 ">
                <form class="form-inline">
                    <a class="btn btn-primary my-2 my-sm-0" href="/controller?command=applicantsList" role="button">
                        <fmt:message key="navbar.Admin_workspace_applicants"/></a>
                </form>

            </li>


            <div class="nav-item dropdown my-2 ml-auto-auto ">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown "><a href="" class="dropdown-toggle" data-target="#navbarDropdownPr"
                                             data-toggle="dropdown"><fmt:message
                            key="navbar.Account"/> ${sessionScope.applicant.username}
                        <b class="caret"></b></a>
                        <div class="dropdown-menu dropdown-menu-right" id="navbarDropdownPr">
                            <a class="dropdown-item" href="controller?command=applicantProfile"><fmt:message
                                    key="navbar.my_profile"/></a>
                            <a class="dropdown-item" href="controller?command=getApplicantRequestsList"><fmt:message
                                    key="navbar.my_requests"/></a>
                            <a class="dropdown-item" href="controller?command=logout"><fmt:message
                                    key="navbar.logout"/></a>
                        </div>

                    </li>
                </ul>
            </div>
        </ul>
    </div>
</nav>
<br>

<div class="container">
    <div class="row">
        <div >
            <div class="panel-body">

                <td align="center">

                    <form class="form-inline my-2 my-lg-0"
                          action="controller" method="get">

                        <input type="hidden" name="command" value="finalizeStatementForFaculty">
                        <input type="hidden" name="facultyId" value="${facultyId}">
                        <button class="btn btn-danger" type="submit"><fmt:message
                                key="statement.finalize_statement"/></button>
                    </form>

                </td>
                <br>
                <table class="table table-striped table-bordered table-responsive ">
                    <thead>
                    <tr>

                        <th><fmt:message key="statement.faculty.name"/></th>
                        <th><fmt:message key="statement.applicant_name"/></th>
                        <th><fmt:message key="statement.applicant_mail"/></th>
                        <th><fmt:message key="statement.applicant_number"/></th>
                        <th><fmt:message key="statement.applicant_grade"/></th>
                        <th><fmt:message key="statement.date_time"/></th>
                        <th><fmt:message key="statement.budget_contract"/></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="req" items="${admissionRequestsList}" varStatus="loop">

                        <tr>

                            <td style="width: 14.28%"><c:choose>
                                <c:when test="${sessionScope.lang eq 'uk'}">
                                    <c:choose>
                                        <c:when test="${empty req.faculty.nameUk}">
                                            ${req.faculty.nameEn}
                                        </c:when>
                                        <c:otherwise>
                                            ${req.faculty.nameUk}
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    ${req.faculty.nameEn}
                                </c:otherwise>
                            </c:choose>


                            </td>
                            <td>${req.applicant.applicantProfile.firstName} ${req.applicant.applicantProfile.lastName} </td>
                            <td>${req.applicant.applicantProfile.email}</td>
                            <td>${req.applicant.applicantProfile.phoneNumber}</td>
                            <td>${req.getSumOfGrades()}</td>
                            <td>${fn:formatDateTime(req.creationDateTime)}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${loop.index < req.faculty.budgetCapacity}">
                                        Budget
                                    </c:when>
                                    <c:otherwise>
                                        Contract
                                    </c:otherwise>
                                </c:choose>


                            </td>

                        </tr>
                    </c:forEach>


                    </tbody>

                </table>


            </div>

        </div>


    </div>
</div>
<br>
<br>


</body>
</html>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>