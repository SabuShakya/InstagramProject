<div ng-include src="'static/views/navigation.html'"></div>

<link rel="stylesheet" type="text/css" href="static/css/profile.css">
<header>
    <div align="center" class="col-md-12">
        <img class="img-responsive img-circle margin" src="uploads/{{searched.pic}}" width="20%" height="20%">
        <p>{{searched.userDisplayName}}</p>
    </div>
</header>

<div class="main">
    <div align="center">
        <div ng-show="searched.showFollowOptionsBtn">
        <div ng-if="searched.showFollowBtn">
            <button type="button" class="btn-default" ng-click="searched.followUser()">Follow</button>
        </div>
        <div ng-hide="searched.showFollowBtn">
            <button type="button" class="btn-default" ng-click="searched.unfollowUser()">Un-Follow</button>
        </div>
        </div>
    </div>
    <br>

    <div align="center">
        <div ng-if="searched.showBlockBtn">
            <button type="button" class="btn-default" ng-click="searched.blockUser()">Block</button>
        </div>
        <div ng-hide="searched.showBlockBtn">
            <button type="button" class="btn-default" ng-click="searched.unblockUser()">UnBlock</button>
        </div>
    </div>

    <div class="stats-container">
        <div class="stat">
            <div class="number">
                <button ng-click="searched.followersList()" ng-disabled="searched.showFollowersList"> {{searched.followers}}</button>
            </div>
            <div class="text" style="color: black">Followers</div>
        </div>

    <div class="stat">
        <div class="number">{{searched.totalPictures}}</div>
        <div class="text" style="color: black">Pictures</div>
    </div>

    <div class="stat">
        <div class="number">
            <button ng-click="searched.followingList()" ng-disabled="searched.showFollowingList"> {{searched.following}}</button>
        </div>
        <div class="text" style="color: black">Following</div>
    </div>
    </div>

</div>
<br>

<div class="container">
    <div align="center">
        <div ng-show="searched.showStatus">
            <span class="glyphicon glyphicon-lock" style="width:100%"></span>
        <h2> This account is private </h2>
        Follow this account to see their photos.
        </div>
    </div>
</div>

<div class="container">
    <div align="center">
<div ng-show="searched.showBlockMessage">
    <span class="glyphicon glyphicon-camera"></span>
    <h2> No posts yet </h2>
</div>
    </div>
</div>

<div ng-show="searched.showPhotoList">
<div class="col-md-4" ng-repeat="photo in searched.photoList">
    <div class="thumbnail">
        <img src="uploads/{{photo.image_path}}" style="width:100%; height: 50%;"
             ng-click="searched.commentModal(photo.image_path,photo.caption)">
    </div>
</div>
</div>


</div>
<br>

<div align="center" ng-show="profile.showFollowMessage">
    <span class="glyphicon glyphicon-plus"></span>
    <span style="color:red">Search users and Follow them to see their posts</span>
</div>

<div ng-hide="profile.showList">
    <div class="col-md-4" ng-repeat="photo in profile.photoList track by $index">
        <div class="thumbnail">
            <img src="uploads/{{photo.image_path}}" style="width:100%"
                 ng-click="profile.commentModal(photo.image_path,photo.caption)">
            </button><br>

        </div>
    </div>


