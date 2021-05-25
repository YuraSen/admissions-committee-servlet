<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Applicant Profile</title>
</head>
<body>
<jsp:include page="../fragments/navbar.jsp"/>
<br>
<jsp:useBean id="applicantProfile" type="com.senin.demo.model.entity.ApplicantProfile" scope="request"/>

<div class="container">
    <div class="row">
        <h2 class="text-danger">Applicant Profile</h2>
        <table class="table table-bordered success">
        <form class="form-inline ml-auto my-2 my-lg-0" action="/controller" method="post">
            <input type="hidden" name="command" value="updateApplicantProfile">

                <thead>

                <tr>
                    <th><label for="firstName">First Name</label></th>
                    <td>
                        <div>
                            <input type="text" id="firstName" class="form-control" name="firstName"
                                   value="${applicantProfile.firstName}" required autofocus>
                        </div>
                    </td>
                </tr>

                <tr>

                    <th><label for="lastName">Last Name</label></th>

                    <td>
                        <div>
                            <input type="text" id="lastName" class="form-control" name="lastName"
                                   value="${applicantProfile.lastName}">
                        </div>
                    </td>
                </tr>
                <tr>

                    <th><label for="email">Email</label></th>
                    <td>
                        <div>
                            <input type="email" id="email" class="form-control" name="email"
                                   value="${applicantProfile.email}" required>
                        </div>
                    </td>
                </tr>

                <tr>
                    <th><label for="address">Address</label></th>
                    <td>
                        <div>
                            <input type="text" id="address" class="form-control" name="address"
                                   value="${applicantProfile.address}">
                        </div>
                    </td>
                </tr>
                <tr>
                    <th><label for="city">City</label></th>
                    <td>
                        <div>
                            <input type="text" id="city" class="form-control" name="city"
                                   value="${applicantProfile.city}">
                        </div>
                    </td>
                </tr>
                <tr>
                    <th><label for="region">Region</label></th>
                    <td>
                        <div>
                            <input type="text" id="region" class="form-control" name="region"
                                   value=" ${applicantProfile.region}">
                        </div>

                    </td>
                </tr>
                <tr>
                    <th><label for="school">School</label></th>
                    <td>
                        <div>
                            <input type="text" id="school" class="form-control" name="school"
                                   value="${applicantProfile.school}">
                        </div>
                    </td>
                </tr>
                <tr>
                    <th><label for="phoneNumber">Contact Number</label></th>
                    <td>
                        <div>
                            <input type="tel" id="phoneNumber" class="form-control" name="phoneNumber"
                                   value="${applicantProfile.phoneNumber}">
                        </div>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <div>
                            <button class="btn btn-primary "
                                    type="submit">Save
                            </button>
                        </div>
                    </td>
                </tr>

                </thead>
            </table>
        </form>
    </div>
</div>
</body>
</html>
