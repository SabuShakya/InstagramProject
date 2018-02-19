<html ng-app='userModule'>
<head>
    <title>Instagram </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <link href="static/css/newsFeed.css">
    <link href="static/css/comments.css" rel="stylesheet">
    <link href="static/css/update.css" rel="stylesheet">
    <%--<link href="static/css/angucomplete.css" rel="stylesheet">--%>
    <%--<link href="static/css/structure.css" rel="stylesheet">--%>

    <link href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script type="text/javascript" src="angular.min.js"></script>
    <script type="text/javascript" src="angular-route.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-animate.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-touch.js"></script>
    <script src="https://cdn.jsdelivr.net/ngstorage/0.3.6/ngStorage.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/2.5.0/ui-bootstrap-tpls.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="ng-infinite-scroll.js"></script>
    <script type="text/javascript" src="angular-base64-upload.js"></script>
    <script type="text/javascript" src="app.js"></script>

</head>
<body>

<div ng-view></div>
</body>

<script src="static/js/controller/SignupController.js"> </script>
<script src="static/js/controller/LoginController.js"> </script>
<script src="static/js/controller/MainController.js"> </script>
<script src="static/js/controller/ImgUploadController.js"> </script>
<script src ="static/js/controller/ProfileController.js"></script>
<script src="static/js/controller/CommentsController.js"></script>
<script src="static/js/controller/UploadProfilePhotoController.js"></script>
<script src="static/js/controller/SearchController.js"></script>
<script src="static/js/controller/SearchedProfileController.js"></script>
<script src="static/js/controller/NavigationController.js"></script>
<script src="static/js/controller/LikesListController.js"></script>
<script src="static/js/controller/UpdateController.js"></script>
<script src="static/js/controller/FollowersListController.js"></script>
<script src="static/js/controller/FollowingListController.js"></script>
<script src="static/js/controller/SearchedFollowersListController.js"></script>
<script src="static/js/controller/SearchedFollowingListController.js"></script>
<script src="static/js/controller/BlockUsersListController.js"></script>
<script src="static/js/controller/PostCommentModalController.js"></script>
<script src="static/js/directives/EnterDirective.js"></script>
<script src="static/js/controller/mainController.js"></script>
<script src="static/js/directives/angucomplete.js"></script>


<script src="static/js/service/HttpService.js"> </script>

</html>