<div ng-include src="'modules/views/DashBoard.html'"></div>
<div ng-include src="'modules/views/breadcrumb.html'"></div>

<link rel="stylesheet" type="text/css" href="css/profile.css">
<header>
    <div align="center" class="col-md-12">
        <img class="img-responsive img-circle margin" src="uploads/{{profile.profilePicture}}" width="20%" height="20%">
        <p>{{profile.userDisplayName}} </p>
    </div>
</header>

<div class="stats-container">
    <div class="stat">
        <div class="number">
        <button type="button" class="btn btn-link" ng-click="profile.followersList()" ng-disabled="profile.showFollowersList"> {{profile.followers}}</button>
    </div>
        <div class="text" style="color: black">Followers</div>
    </div>

    <div class="stat">
        <div class="number">{{profile.totalPictures}}</div>
        <div class="text" style="color: black">Pictures</div>
    </div>

    <div class="stat">
        <div class="number">
            <button type="button" class="btn btn-link" ng-click="profile.followingList()" ng-disabled="profile.showFollowingList"> {{profile.following}}</button>
        </div>
        <div class="text" style="color: black">Following</div>
    </div>
</div>
<br>

<div class="col-md-4" ng-repeat="photo in profile.photoList">
    <div class="thumbnail">
        <img src="uploads/{{photo.image_path}}" style="width:100%; height: 45%;"
             ng-click="profile.commentModal(photo.image_path,photo.caption)">
    </div>
</div>