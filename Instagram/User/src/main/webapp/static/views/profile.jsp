<div ng-include src="'static/views/navigation.html'"></div>

<link rel="stylesheet" type="text/css" href="static/css/profile.css">
<header>
    <button class="btn btn-success btn-lg pull-right" ng-click="profile.openModal()"> Upload Photo</button>
    <div align="center" class="col-md-12">
        <img class="img-responsive img-circle margin" src="uploads/{{pic}}" width="30%" height="30%">
    </div>
</header>

<div class="main">
    <p class="description"> {{profile.userDisplayName}}</p>
    <div class="wrapper">
        <button class="btn btn-success" ng-click="profile.editProfile()">Upload profile photo</button>
    </div>

    <div class="stats-container">
        <div class="stat">
            <%--<button ng-click="profile.followersList()"> {{profile.followers}}</button>--%>
            <a href="#" ng-click="profile.followersList()"> {{profile.followers}}</a>
            <div class="number">
                <a href="#" ng-click="profile.followersList()">Followers</a>
            </div>
        </div>
        <div class="stat">
            <div class="number">{{profile.totalPictures}}</div>
            <div class="text">Pictures</div>
        </div>
        <div class="stat">
            <%--<button ng-click="profile.followingList()"> {{profile.following}}</button>--%>
            <a href="#" ng-click="profile.followingList()"> {{profile.following}}</a>
            <div class="number">
                <a href="#" ng-click="profile.followingList()">Following</a>
            </div>
        </div>
    </div>
</div>
<br>

<div ng-hide="profile.showList">
    <div class="col-md-4" ng-repeat="photo in profile.photoList track by $index">
        <div class="thumbnail">
            <img src="uploads/{{photo.image_path}}" style="width:100%"
                 ng-click="profile.commentModal(photo.image_path,photo.caption)">
            </button><br>

        </div>
    </div>
</div>