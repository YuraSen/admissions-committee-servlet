<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../fragments/navbar.jsp" />
<jsp:useBean id="applicant" type="com.senin.demo.model.entity.Applicant" scope="request"/>
<br>
<main class="login-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Admission request to ${requestScope.facultyName} </div>
                    <div class="card-body">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="submitRequest">

                            <div class="form-group row">
                                <label for="first_name" class="col-md-4 col-form-label text-md-right">First
                                    Name </label>
                                <div class="col-md-6" id="first_name">
                                    ${applicant.applicantProfile.firstName}
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="last_name" class="col-md-4 col-form-label text-md-right">Last
                                    Name </label>
                                <div class="col-md-6" id="last_name">
                                    ${applicant.applicantProfile.lastName}
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right">E-Mail
                                    Address</label>
                                <div class="col-md-6" id="email_address">
                                    ${applicant.applicantProfile.email}
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="address" class="col-md-4 col-form-label text-md-right">Address </label>
                                <div class="col-md-6" id="address">
                                    ${applicant.applicantProfile.address}
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="phone_number" class="col-md-4 col-form-label text-md-right">Contact
                                    Number
                                </label>
                                <div class="col-md-6" id="phone_number">
                                    ${applicant.applicantProfile.phoneNumber}
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject1Grade" class="col-md-4 col-form-label text-md-right">Grade For ${faculty.getRequiredSubject1()} </label>
                                <div class="col-md-6">
                                    <input type="number" id="requiredSubject1Grade" min="1" max="12" class="form-control" name="requiredSubject1Grade" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject2Grade" class="col-md-4 col-form-label text-md-right">Grade For ${faculty.getRequiredSubject2()} </label>
                                <div class="col-md-6">
                                    <input type="number" id="requiredSubject2Grade" min="1" max="12" class="form-control" name="requiredSubject2Grade" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject3Grade"  class="col-md-4 col-form-label text-md-right">Grade For ${faculty.getRequiredSubject3()} </label>
                                <div class="col-md-6">
                                    <input type="number" id="requiredSubject3Grade" min="1" max="12" class="form-control" name="requiredSubject3Grade" required>
                                </div>
                            </div>

                            <input type="hidden" id="applicantId" name="applicantId" value="${applicant.id}"/>
                            <input type="hidden" id="facultyId" name="facultyId" value="${requestScope.facultyId}"/>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Submit Request
                                </button>
                            </div>

                        </form>
                    </#if>
                </div>
            </div>
        </div>
    </div>
    </div>

</main>


</body>
</html>
