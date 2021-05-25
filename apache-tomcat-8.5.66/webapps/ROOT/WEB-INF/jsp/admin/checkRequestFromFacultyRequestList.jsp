<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../fragments/navbar.jsp"/>
<jsp:useBean id="admissionRequest" type="com.senin.demo.model.entity.AdmissionRequest" scope="request"/>
<br>
<div class="container">
    <div class="row">
        <h2 class="text-danger">Request to the ${admissionRequest.faculty.name} </h2>

        <table class="table table-bordered success">
            <thead>
            <tr>
                <th>First Name</th>
                <td>${admissionRequest.applicant.applicantProfile.firstName}</td>
            </tr>
            <tr>
                <th>Last Name</th>
                <td>${admissionRequest.applicant.applicantProfile.lastName}</td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${admissionRequest.applicant.applicantProfile.email}</td>
            </tr>
            <tr>
                <th>Address</th>
                <td>${admissionRequest.applicant.applicantProfile.address}</td>
            </tr>

            <tr>
                <th>City</th>
                <td>${admissionRequest.applicant.applicantProfile.city}</td>
            </tr>
            <tr>
                <th>Region</th>
                <td>${admissionRequest.applicant.applicantProfile.region}</td>
            </tr>
            <tr>
                <th>School</th>
                <td>${admissionRequest.applicant.applicantProfile.school}</td>
            </tr>
            <tr>
                <th>Contact Number</th>
                <td>${admissionRequest.applicant.applicantProfile.phoneNumber}</td>
            </tr>

            <tr>
                <th>Grade for ${admissionRequest.faculty.requiredSubject1} </th>
                <td>${admissionRequest.faculty.requiredSubject1}</td>
            </tr>
            <tr>
                <th>Grade for ${admissionRequest.faculty.requiredSubject2}</th>
                <td>${admissionRequest.faculty.requiredSubject2}</td>
            </tr>
            <tr>
                <th>Grade for ${admissionRequest.faculty.requiredSubject3}</th>
                <td>${admissionRequest.faculty.requiredSubject3}</td>
            </tr>

            </thead>

        </table>

        <div class="container h-100">
            <div class="row h-100 justify-content-center align-items-center">

                <form action="controller" method="POST">
                    <input type="hidden" id="command" name="command" value="changeAdmissionRequestStatus">
                    <input type="hidden" id="id" name="id" value="${admissionRequest.id}">
                    <input type="hidden" id="facultyId" name="facultyId" value="${admissionRequest.faculty.id}">
                    <input type="hidden" id="facultyName" name="facultyName" value="${admissionRequest.faculty.name}">
                    <input type="hidden" id="admissionRequestStatus" name="admissionRequestStatus" value="APPROVED">
                    <button class="btn btn-primary mr-2" type="submit">Approve</button>
                </form>
                <form action="controller" method="POST">
                    <input type="hidden" id="command" name="command" value="changeAdmissionRequestStatus">
                    <input type="hidden" id="id" name="id" value="${admissionRequest.id}">
                    <input type="hidden" id="facultyId" name="facultyId" value="${admissionRequest.faculty.id}">
                    <input type="hidden" id="admissionRequestStatus" name="admissionRequestStatus" value="REJECTED">
                    <button class="btn btn-danger mr-2" type="submit">Reject</button>
                </form>
                <form>
                    <button class="btn btn-warning mr-2" onclick="window.history.back()" type="button"> Back</button>
                </form>

            </div>
        </div>
    </div>
</div>


</body>
</html>
