<div ng-include src="'static/views/navigation.html'"></div>

<link rel="stylesheet" type="text/css" href="static/css/profile.css">
<header>
    <button class="btn btn-success btn-lg pull-right" ng-click="profile.openModal()"> Upload Photo</button>
    <div class="profile-pic-container">
        <%--<img class="img-responsive img-circle margin" style="display:inline" alt="" width="120%"--%>
             <%--src="https://s3.amazonaws.com/uifaces/faces/twitter/nuraika/128.jpg" alt=""/>--%>
            <img src="uploads/{{pic}}" style="width:100%">
    </div>

</header>

<div class="main">
    <p class="description"> {{profile.userDisplayName}}</p>
    <div class="wrapper">
        <button class="btn btn-success" ng-click="profile.editProfile()">Upload profile photo</button>
    </div>

    <div class="stats-container">
        <div class="stat">
            <div class="number">{{profile.followers}}</div>
            <div class="text">Followers</div>
        </div>
        <div class="stat active">
            <div class="number">{{profile.totalPictures}}</div>
            <div class="text">Pictures</div>
        </div>
        <div class="stat">
            <div class="number">{{profile.following}}</div>
            <div class="text">Following</div>
        </div>
    </div>
</div>
<br>

<div ng-hide ="profile.showList">
<div class="alert alert-success" ng-show="saved">
<strong>{{message}}</strong>
</div>

<div class="col-md-4" ng-repeat="photo in profile.photoList">
    <div class="thumbnail" >
        <img src="uploads/{{photo.image_path}}" style="width:100%" ng-click="profile.commentModal(photo.image_path,photo.caption)">
        {{photo.caption}}
    </div>
</div>

</div>