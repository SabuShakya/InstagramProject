<div ng-include src="'static/views/navigation.html'"></div>

<link rel="stylesheet" type="text/css" href="static/css/profile.css">
<header>
    <div align="center" class="col-md-12">
        <img class="img-responsive img-circle margin" src="uploads/{{uppc.pic}}" width="30%" height="30%">
    </div>
</header>

<div class="main">
    <p align="center" class="description">{{uppc.userDisplayName}}</p><br>
    <div align="center">
        <div ng-if="uppc.showFollowBtn">
            <button type="button" class="btn-default" ng-click="uppc.followUser()">Follow</button>
        </div>
        <div ng-hide="uppc.showFollowBtn">
            <button type="button" class="btn-default" ng-click="uppc.unfollowUser()">Un-Follow</button>
        </div>
    </div>
    <div class="stats-container">
        <div class="stat">
            <div class="number">{{uppc.followers}}</div>
            <div class="text">Followers</div>
        </div>
        <div class="stat active">
            <div class="number">{{uppc.totalPictures}}</div>
            <div class="text">Pictures</div>
        </div>
        <div class="stat">
            <div class="number">{{uppc.following}}</div>
            <div class="text">Following</div>
        </div>
    </div>
</div>
<br>

<div class="col-md-4" ng-repeat="photo in uppc.photoList">
    <div class="thumbnail">
        <img src="uploads/{{photo.image_path}}" style="width:100%"
             ng-click="uppc.commentModal(photo.image_path,photo.caption)">
        {{photo.caption}}
    </div>
</div>

