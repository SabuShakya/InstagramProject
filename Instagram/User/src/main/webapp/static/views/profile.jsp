<div ng-include src="'static/views/navigation.html'"></div>

<link rel="stylesheet" type="text/css" href="static/css/profile.css">
<header>
    <button class="btn btn-success btn-lg pull-right" ng-click="profile.openModal()"> Upload Photo</button>
    <button class="btn btn-danger btn-lg pull-right" ng-click="profile.logout()">Logout</button>
    <button class="btn btn-success btn-lg pull-right" ng-click="profile.editProfile()"></button>
    <div class="profile-pic-container">
        <img class="img-responsive img-circle margin" style="display:inline" alt="" width="120%"
             src="uploads/{{photo.profileImg}}" alt="" />
    </div>
</header>

<div class="main">
    <p class="description"> {{profile.userDisplayName}}</p>

    <div class="stats-container">
        <div class="stat">
            <div class="number">3641</div>
            <div class="text">Likes</div>
        </div>
        <div class="stat">
            <div class="number">575</div>
            <div class="text">Follower</div>
        </div>
        <div class="stat active">
            <div class="number">147</div>
            <div class="text">Pictures</div>
        </div>
        <div class="stat">
            <div class="number">386</div>
            <div class="text">following</div>
        </div>
    </div>
</div>
<br>

<div class="col-md-4" ng-repeat="photo in profile.photoList">
    <div class="thumbnail" >
        <%--<img src="uploads/{{photo.profileImg}}">--%>
        <img src="uploads/{{photo.image_path}}" style="width:100%" ng-click="profile.commentModal(photo.image_path,photo.caption)">
        {{photo.caption}}
    </div>
</div>


