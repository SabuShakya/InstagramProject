<div ng-include src="'modules/views/DashBoard.html'"></div>
<div ng-include src="'modules/views/breadcrumb.html'"></div>

<link rel="stylesheet" type="text/css" href="static/css/profile.css">
<header>
    <div align="center" class="col-md-12">
        <img class="img-responsive img-circle margin" src="uploads/{{profile.profilePicture}}" width="30%" height="30%">
    </div>
</header>

<div class="main">
    <p align="center" class="description">{{profile.userDisplayName}}</p><br>
    <%--<div align="center">--%>
        <%--<div ng-show="profile.showFollowOptionsBtn">--%>
            <%--<div ng-if="searched.showFollowBtn">--%>
                <%--<button type="button" class="btn-default" ng-click="searched.followUser()">Follow</button>--%>
            <%--</div>--%>
            <%--<div ng-hide="searched.showFollowBtn">--%>
                <%--<button type="button" class="btn-default" ng-click="searched.unfollowUser()">Un-Follow</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<br>--%>

    <%--<div align="center">--%>
        <%--<div ng-if="searched.showBlockBtn">--%>
            <%--<button type="button" class="btn-default" ng-click="searched.blockUser()">Block</button>--%>
        <%--</div>--%>
        <%--<div ng-hide="searched.showBlockBtn">--%>
            <%--<button type="button" class="btn-default" ng-click="searched.unblockUser()">UnBlock</button>--%>
        <%--</div>--%>
    <%--</div>--%>

    <div class="stats-container">
        <div class="stat">
            <div class="number">{{profile.followers}}</div>
            <button ng-click="profile.followersList()"> Followers</button>
        </div>
        <div class="stat">
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

<%--<div class="container">--%>
    <%--<div align="center">--%>
        <%--<div ng-show="searched.showStatus">--%>
            <%--<span class="glyphicon glyphicon-lock" style="width:100%"></span>--%>
            <%--<h2> This account is private </h2>--%>
            <%--Follow this account to see their photos.--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--<div class="container">--%>
    <%--<div align="center">--%>
        <%--<div ng-show="searched.showBlockMessage">--%>
            <%--<span class="glyphicon glyphicon-camera"></span>--%>
            <%--<h2> No posts yet </h2>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>


    <div class="col-md-4" ng-repeat="photo in profile.photoList">
        <div class="thumbnail">
            <img src="uploads/{{photo.image_path}}" style="width:100%"
                 ng-click="profile.commentModal(photo.image_path,photo.caption)">
            {{photo.caption}}
        </div>
    </div>