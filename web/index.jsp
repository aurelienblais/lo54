<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/toastr.min.css"/>
    <title>Super Duper School</title>

</head>
<body class="bg-light">
<div class="container">
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" href="#courses" data-toggle="tab">Courses</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#course_sessions" data-toggle="tab">Sessions</a>
        </li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane fade show active" id="courses">
            <%@ include file="partials/courses/list.jsp" %>
        </div>
        <div class="tab-pane fade" id="course_sessions">
            <%@ include file="partials/course_sessions/list.jsp" %>
        </div>
    </div>
</div>

<%@ include file="partials/courses/templates.html" %>
<%@ include file="partials/course_sessions/templates.html" %>

<script src="assets/js/jquery-3.4.1.min.js"></script>
<script src="assets/js/popper.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/handlebars-latest.js"></script>
<script src="https://kit.fontawesome.com/86ec1904d0.js" crossorigin="anonymous"></script>
<script src="assets/js/jquery.typewatch.js"></script>
<script src="assets/js/toastr.min.js"></script>

<script src="assets/js/utils.js"></script>
<script src="assets/js/courses.js"></script>
<script src="assets/js/course_sessions.js"></script>
<script src="assets/js/global.js"></script>
</body>
</html>
