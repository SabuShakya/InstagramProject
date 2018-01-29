<div ng-include src="'static/views/navigation.html'"></div>

<link rel="stylesheet" type="text/css" href="static/css/profile.css">
<header>
    <button class="btn btn-success btn-lg pull-right" ng-click="profile.openModal()"> Upload Photo</button>
    <%--<img class="img-responsive img-circle margin" style="display:inline" alt="" width="120%"--%>
    <%--src="https://s3.amazonaws.com/uifaces/faces/twitter/nuraika/128.jpg" alt=""/>--%>
    <div align="center" class="col-md-12">
        <img class="img-responsive img-circle margin" src="uploads/{{pic}}" width="30%" height="30%">
    </div>
</header>

<div class="main">
    <%--<p class="description"> {{profile.userDisplayName}}</p>--%>
    <div class="wrapper">
        <button class="btn btn-success" ng-click="profile.editProfile()">Upload profile photo</button>
    </div>

    <div class="stats-container">
        <div class="stat">
            <div class="number">{{profile.followers}}</div>
            <button ng-click="profile.followersList()"> Followers</button>
        </div>
        <div class="stat active">
            <div class="number">{{profile.totalPictures}}</div>
            <div class="text">Pictures</div>
        </div>
        <div class="stat">
            <div class="number">{{profile.following}}</div>
            <button ng-click="profile.followingList()"> Following</button>
        </div>
    </div>
</div>
<br>

<div ng-hide="profile.showList">
    <div class="col-md-4" ng-repeat="photo in profile.photoList">
        <div class="thumbnail">
            <img src="uploads/{{photo.image_path}}" style="width:100%"
                 ng-click="profile.commentModal(photo.image_path,photo.caption)">
            {{photo.caption}}
        </div>
    </div>
</div>