<html ng-app='userModule'>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-animate.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-touch.js"></script>
    <script src="https://cdn.jsdelivr.net/ngstorage/0.3.6/ngStorage.min.js"></script>

    <script type="text/javascript" src="angular-base64-upload.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/2.5.0/ui-bootstrap-tpls.min.js"></script>
    
    <script type="text/javascript" src="app.js"></script>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

<div ng-view></div>

<script src="static/js/controller/SignupController.js"> </script>
<script src="static/js/controller/LoginController.js"> </script>
<script src="static/js/controller/MainController.js"> </script>
<script src="static/js/controller/ImgUploadController.js"> </script>
<script src ="static/js/controller/ModalUploadController.js"></script>

<script src="static/js/service/HttpService.js"> </script>
<script src="static/js/service/SignupService.js"> </script>

</body>
</html>