<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app="adminModule">
<head>
    <meta charset="utf-8">
    <title>Welcome</title>

    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link href="css/login.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular.js"></script>--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular-route.min.js"></script>--%>
    <script type="text/javascript" src="static/angular.min.js"></script>
    <script type="text/javascript" src="static/angular-route.min.js"></script>
    <%--uiModal--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular-animate.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.5/angular-touch.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/2.5.0/ui-bootstrap-tpls.min.js"></script>
    <%--localStorage--%>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/ngstorage/0.3.6/ngStorage.min.js"></script>
    <%--module--%>
    <script type="text/javascript" src="app-module.js"></script>
    <%--for photo upload--%>
    <script type="text/javascript" src="static/angular-base64-upload.js"></script>

</head>
<body>
<div>
<ng-view></ng-view>
</div>
</body>
<%--Controllers--%>
<script type="text/javascript" src="modules/adminsetup/controller/LoginController.js"></script>
<script type="text/javascript" src="modules/adminsetup/controller/SignupController.js"></script>
<script type="text/javascript" src="modules/adminsetup/controller/AdminpageController.js"></script>
<script type="text/javascript" src="modules/adminsetup/controller/NewAdminController.js"></script>
<script type ="text/javascript" src="modules/adminsetup/controller/ManageAdminController.js"></script>
<script type="text/javascript" src="modules/adminsetup/controller/EditModalController.js"></script>
<%--<script type="text/javascript" src="modules/adminsetup/controller/LogOutController.js"></script>--%>
<script type="text/javascript" src="modules/adminsetup/controller/UserLogController.js"></script>

<%--Services--%>
<script type="text/javascript" src="modules/adminsetup/service/LoginService.js"></script>
<script type="text/javascript" src="modules/adminsetup/service/SignupService.js"></script>
<script type="text/javascript" src="modules/adminsetup/service/HttpService.js"></script>
<script type="text/javascript" src="modules/adminsetup/service/NewAdminService.js"></script>

</html>
