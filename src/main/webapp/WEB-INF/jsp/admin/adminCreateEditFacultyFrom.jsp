<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../fragments/navbar.jsp" />
<c:choose>
    <c:when test="${requestScope.action=='create'}">


<br>
<main class="registration-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Create new faculty</div>
                    <div class="card-body">
                        <@errors.errors/>
                        <form action="/controller" method="POST">
                            <input type="hidden" name="command" value="createNewFaculty">
                            <div class="form-group row">
                                <label for="name" class="col-md-4 col-form-label text-md-right">Faculty Name </label>
                                <div class="col-md-6">
                                    <input type="text" id="name" class="form-control" name="name"
                                           required autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="description" class="col-md-4 col-form-label text-md-right">Description</label>
                                <div class="col-md-6">
                                    <input type="text" id="description" class="form-control" name="description">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="budgetCapacity" class="col-md-4 col-form-label text-md-right"> Budget Capacity </label>
                                <div class="col-md-6">
                                    <input type="number" id="budgetCapacity" class="form-control" name="budgetCapacity"
                                           required >
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="totalCapacity" class="col-md-4 col-form-label text-md-right">Total Capacity</label>
                                <div class="col-md-6">
                                    <input type="number" id="totalCapacity" class="form-control" name="totalCapacity" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="requiredSubject1" class="col-md-4 col-form-label text-md-right">Required Subject1 </label>
                                <div class="col-md-6">
                                    <input type="text" id="requiredSubject1" class="form-control" name="requiredSubject1" required>
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="requiredSubject2" class="col-md-4 col-form-label text-md-right">Required Subject2 </label>
                                <div class="col-md-6">
                                    <input type="text" id="requiredSubject2" class="form-control" name="requiredSubject2" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="requiredSubject3" class="col-md-4 col-form-label text-md-right">Required Subject3 </label>
                                <div class="col-md-6">
                                    <input type="text" id="requiredSubject3" class="form-control" name="requiredSubject3" required>
                                </div>
                            </div>



                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Create
                                </button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>

    </c:when>
    <c:otherwise>
        <jsp:useBean id="faculty" scope="request" type="entity.Faculty"/>

        <br>
        <main class="registration-form">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-header">Update faculty information</div>
                            <div class="card-body">
                                <@errors.errors/>
                                <form action="controller" method="POST">
                                    <input type="hidden" name="command" value="updateFaculty">
                                    <input type="hidden" name="facultyId" value="${faculty.id}">
                                    <div class="form-group row">
                                        <label for="name" class="col-md-4 col-form-label text-md-right">Faculty Name </label>
                                        <div class="col-md-6">
                                            <input type="text" id="name" class="form-control" name="name" value="${faculty.name}"
                                                   required autofocus>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="description" class="col-md-4 col-form-label text-md-right">Description</label>
                                        <div class="col-md-6">
                                            <input type="text" id="description" class="form-control" name="description" value="${faculty.description}">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="budgetCapacity" class="col-md-4 col-form-label text-md-right"> Budget Capacity </label>
                                        <div class="col-md-6">
                                            <input type="number" id="budgetCapacity" class="form-control" name="budgetCapacity" value="${faculty.budgetCapacity}"
                                                   required >
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="totalCapacity" class="col-md-4 col-form-label text-md-right">Total Capacity</label>
                                        <div class="col-md-6">
                                            <input type="number" id="totalCapacity" class="form-control" name="totalCapacity" value="${faculty.totalCapacity}" required>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="requiredSubject1" class="col-md-4 col-form-label text-md-right">Required Subject1 </label>
                                        <div class="col-md-6">
                                            <input type="text" id="requiredSubject1" class="form-control" name="requiredSubject1" value="${faculty.requiredSubject1}" required>
                                        </div>
                                    </div>


                                    <div class="form-group row">
                                        <label for="requiredSubject2" class="col-md-4 col-form-label text-md-right">Required Subject2 </label>
                                        <div class="col-md-6">
                                            <input type="text" id="requiredSubject2" class="form-control" name="requiredSubject2" value="${faculty.requiredSubject2}" required>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="requiredSubject3" class="col-md-4 col-form-label text-md-right">Required Subject3 </label>
                                        <div class="col-md-6">
                                            <input type="text" id="requiredSubject3" class="form-control" name="requiredSubject3" value="${faculty.requiredSubject3}" required>
                                        </div>
                                    </div>



                                    <div class="col-md-6 offset-md-4">
                                        <button type="submit" class="btn btn-primary">
                                           Update
                                        </button>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </main>



    </c:otherwise>
</c:choose>

</body>
</html>
