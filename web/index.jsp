<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <title>$Title$</title>

</head>
<body class="bg-light">
<div class="container">
    <ul class="nav nav-tabs" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" id="courses-tab" data-toggle="tab" href="#courses" role="tab">Cours</a>
        </li>
    </ul>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="courses" role="tabpanel">
            <%@ include file="partials/courses/list.jsp" %>
        </div>
    </div>
</div>

<%@ include file="partials/courses/templates.html" %>

<script src="assets/js/jquery-3.4.1.min.js"></script>
<script src="assets/js/popper.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/handlebars-latest.js"></script>
<script src="https://kit.fontawesome.com/86ec1904d0.js" crossorigin="anonymous"></script>

<script src="assets/js/courses.js"></script>
<script src="assets/js/global.js"></script>
</body>
</html>
