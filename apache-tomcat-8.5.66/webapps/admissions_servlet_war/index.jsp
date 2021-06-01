<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>

<html lang="${sessionScope.lang}">
<c:set var="title" value="Faculties" scope="page"/>
<head>
    <title>Admission Board Application</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">


    <a class="navbar-brand" href="/"><fmt:message
            key="navbar.Admission_Board_App"/></a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav">

            <li class="nav-item dropdown">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
                                            data-target="#navbarDropdown"><fmt:message key="navbar.Change_Language"/>
                        <b class="caret"></b></a>
                        <div class="dropdown-menu dropdown-menu-right" id="navbarDropdown">

                            <a class="dropdown-item" href="/?sessionLocale=en">
                                <fmt:message key="navbar.English"/></a>
                            <a class="dropdown-item" href="/?sessionLocale=uk">
                                <fmt:message key="navbar.Ukrainian"/></a>
                        </div>

                    </li>
                </ul>
            </li>


            <c:choose>
            <c:when test="${sessionScope.applicant!=null}">
            <div class="nav-item dropdown  ml-auto">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown ml-auto"><a href="" class="dropdown-toggle" data-target="#navbarDropdownPr"
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

                        </form>
                    </li>
                </ul>
            </div>

            </c:when>
            <c:otherwise>
            <ul class="navbar-nav ">
                <div class="nav-item m">

                    <form class="form-inline my-2 mr-2 my-lg-0">
                        <a class="btn btn-primary my-2 my-sm-0" href="/controller?command=loginForm" role="button">
                            <fmt:message key="navbar.login"/></a>
                    </form>
                </div>
                <div class="nav-item ">

                    <form class="form-inline my-2 my-lg-0">
                        <a class="btn btn-primary my-2 my-sm-0" href="/controller?command=registrationForm"
                           role="button">
                            <fmt:message key="navbar.registration"/></a>
                    </form>

                </div>
            </ul>

            </c:otherwise>
            </c:choose>
    </div>
</nav>

<br>
<br>
<br>
<div class="container">

    <p style="line-height: 1; text-align: left;"><strong><span
            style="font-family: Verdana, Geneva, sans-serif; font-size: 14px;">Приймальна комісія&nbsp;</span></strong>
    </p>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
            style="font-family: Verdana, Geneva, sans-serif;">Система має перелік факультетів, для якого необхідно реалізувати можливість сортування:</span></span>
    </p>
    <ul>
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                style="font-family: Verdana, Geneva, sans-serif;"> - по імені (a-z, z-a);&nbsp;</span></span>
        </li>
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                style="font-family: Verdana, Geneva, sans-serif;">&nbsp;- за кількістю бюджетних місць;</span></span>
        </li>
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                style="font-family: Verdana, Geneva, sans-serif;">&nbsp;- за загальною кількістю місць. </span></span>
        </li>
    </ul>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
            style="font-family: Verdana, Geneva, sans-serif;">Абітурієнт реєструється в системі. Під час реєстрації необхідно ввести ПІБ, email, місто, область, назву навчального закладу (опціонально: прикріпити скан атестату з оцінками).</span></span>
    </p>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
            style="font-family: Verdana, Geneva, sans-serif;">&nbsp;Абітурієнт може зареєструватися на один або декілька факультетів. При реєстрації на факультет студент вводить результати з відповідних предметів, а також оцінки з атестату.</span></span>
    </p>
    <p style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
            style="font-family: Verdana, Geneva, sans-serif;">&nbsp;Адміністратор системи володіє правами:</span></span>
    </p>
    <ul>
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                style="font-family: Verdana, Geneva, sans-serif;">&nbsp;- додавання, видалення або редагування факультету; &nbsp;</span></span>
        </li>
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                style="font-family: Verdana, Geneva, sans-serif;">&nbsp; - блокування або &nbsp;розблокування абітурієнта;&nbsp;</span></span>
        </li>

        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                style="font-family: Verdana, Geneva, sans-serif;">&nbsp;- додавання результатів абітурієнтів до Відомості;</span></span>
        </li>
        <li style="line-height: 1; text-align: left;"><span style="font-size: 12px;"><span
                style="font-family: Verdana, Geneva, sans-serif;">&nbsp;- фіналізації Відомості на зарахування. </span></span>
        </li>
    </ul>
    <p style="line-height: 1; text-align: left;"><span
            style="font-family: Verdana, Geneva, sans-serif; font-size: 12px;">Після фіналізації відомості система підраховує суму балів і визначає абітурієнтів, зарахованих до навчального закладу на бюджетні місця, на контракт.</span>
    </p>
    <p style="line-height: 1; text-align: left;"><span
            style="font-family: Verdana, Geneva, sans-serif; font-size: 12px;">       (За бажанням додати оповіщення про результат зарахування на певну форму навчання, а також не зарахування за допомогою відправки email абітурієнтові).</span>
    </p>

</div>
<br>
<br>

<jsp:include page="WEB-INF/jsp/fragments/footer.jsp"></jsp:include>

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