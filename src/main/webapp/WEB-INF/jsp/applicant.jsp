<%@ page  contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
    <title>Admission Board App</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #D6EAF8 ">
        <div>
            <a href="../../index.jsp" class="navbar-brand" style="color:black" > Admission Board App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="/controller"  style="color:black" class="nav-link">Applicant</a></li>
        </ul>
        <ul class="navbar-nav">
            <li><a href="/controller"    style="color:black" class="nav-link">Faculties</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">


    <div class="container">

        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                New User</a>

        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Country</th>
                <th>Actions</th>


            </tr>
            </thead>
            <tbody>

            <c:forEach var="applicant" items="${applicantsList}">

                <tr>
                    <td>
                        <c:out value="${applicant.id}" />
                    </td>
                    <td>
                        <c:out value="${applicant.login}" />
                    </td>
                    <td>
                        <c:out value="${applicant.email}" />
                    </td>
                    <td>

                    </td>
                    <td><a href="edit?id=<c:out value='${applicant.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${applicant.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>

</html>