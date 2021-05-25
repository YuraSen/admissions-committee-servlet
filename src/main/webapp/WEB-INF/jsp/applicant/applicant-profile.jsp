<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Applicant Profile</title>
</head>
<body>
<jsp:include page="../fragments/navbar.jsp" />
<br>
<jsp:useBean id="applicantProfile" type="com.senin.demo.model.entity.ApplicantProfile" scope="request"/>

<div class="container">
    <div class="row">
        <h2 class="text-danger">Applicant Profile</h2>
        <form class="form-inline ml-auto my-2 my-lg-0">
        <a class="btn btn-primary" href="/controller?command=applicantProfileEdit" role="button">Edit Profile</a>
        </form>

        <table class="table table-bordered success">
            <thead>

            <tr>
                <th>First Name</th>
                <td>${applicantProfile.firstName}</td>
            </tr>
            <tr>
                <th class="info">Last Name</th>
                <td>${applicantProfile.lastName}</td>
            </tr>
            <tr>
                <th class="info">Email</th>
                <td>${applicantProfile.email}</td>
            </tr>

            <tr>
                <th class="info">Address</th>
                <td>${applicantProfile.address}</td>
            </tr>
            <tr>
                <th class="info">City</th>
                <td>${applicantProfile.city}</td>
            </tr>

            <tr>
                <th class="info">Region</th>
                <td>${applicantProfile.region}</td>
            </tr>
            <tr>
                <th class="info">School</th>
                <td>${applicantProfile.school}</td>
            </tr>
            <tr>
                <th class="info">Phone number</th>
                <td>${applicantProfile.phoneNumber}</td>
            </tr>

            </thead>

        </table>
    </div>
</div>
</body>
</html>
