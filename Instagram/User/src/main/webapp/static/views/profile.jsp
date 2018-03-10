<div ng-include src="'static/views/navigation.html'"></div>

<link rel="stylesheet" type="text/css" href="static/css/profile.css">
<header>
    <button class="btn btn-success btn-sm pull-right" ng-click="profile.openModal()"> Upload Photo</button>
    <div align="center" class="col-md-12">
        <img class="img-responsive img-circle margin" src="uploads/{{pic}}" width="20%" height="20%">
        <p> {{profile.userDisplayName}}</p>
        <button class="btn btn-success btn-sm" ng-click="profile.editProfile()">Upload profile photo</button>
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
</div>
<br>

<div ng-hide="profile.showList">
    <div class="col-md-4" ng-repeat="photo in profile.photoList track by $index">
        <div class="thumbnail">
            <img src="uploads/{{photo.image_path}}" style="width:100%; height: 50%;"
                 ng-click="profile.commentModal(photo.image_path,photo.caption)">
        </div>
    </div>
</div>