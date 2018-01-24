<div ng-include src="'static/views/navigation.html'"></div>

<link rel="stylesheet" type="text/css" href="static/css/profile.css">
<header>
    <div class="profile-pic-container">
        <img class="img-responsive img-circle margin" style="display:inline" alt="" width="120%"
             src="https://s3.amazonaws.com/uifaces/faces/twitter/nuraika/128.jpg" alt=""/>
    </div>
</header>

<div class="main">
    <p align="center" class="description">{{searched.userDisplayName}}</p><br>
    <div align="center">
        <div ng-if="searched.showFollowBtn">
            <button type="button" class="btn-default" ng-click="searched.followUser()">Follow</button>
        </div>
        <div ng-hide="searched.showFollowBtn">
            <button type="button" class="btn-default" ng-click="searched.unfollowUser()">Un-Follow</button>
        </div>
    </div>
    <div class="stats-container">
        <div class="stat">
            <div class="number">{{searched.followers}}</div>
            <div class="text">Followers</div>
        </div>
        <div class="stat active">
            <div class="number">{{searched.totalPictures}}</div>
            <div class="text">Pictures</div>
        </div>
        <div class="stat">
            <div class="number">{{searched.following}}</div>
            <div class="text">Following</div>
        </div>
    </div>
</div>
<br>

<div class="col-md-4" ng-repeat="photo in searched.photoList">
    <div class="thumbnail">
        <img src="uploads/{{photo.image_path}}" style="width:100%"
             ng-click="searched.commentModal(photo.image_path,photo.caption)">
        {{photo.caption}}
    </div>
</div>

