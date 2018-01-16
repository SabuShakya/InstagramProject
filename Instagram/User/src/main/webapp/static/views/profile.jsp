<%--<div class="container" ng-hide="value">--%>
    <%--<button type="button" class="btn btn-default btn-lg"  ng-click="temp.likes = temp.likes+1">--%>
        <%--<span class="glyphicon glyphicon-heart"></span>{{temp.likes}}--%>
    <%--</button>--%>

<%--</div>--%>
<%--<div ng-hide ="profile.showList">--%>
    <%--<div class="alert alert-success" ng-show="saved">--%>
        <%--<strong>{{message}}</strong>--%>
    <%--</div>--%>

<%--<div ng-repeat="photo in profile.photoList">--%>
    <%--<tr>--%>
        <%--<td><img src="uploads/{{photo.image_path}}"></td>--%>
        <%--<td>{{photo.caption}}</td>--%>
        <%--<td>{{photo.likes}}</td>--%>
        <%--<td>{{photo.comments}}</td>--%>
    <%--</tr>--%>
<%--</div>--%>

<%--</div>--%>
<link rel="stylesheet" type="text/css" href="static/css/profile.css">
<button ng-click="profile.openModal()" class="btn btn-success btn-lg pull-right"> Upload Photo</button>
<button class="btn btn-danger btn-lg pull-right" ng-click="profile.logout()">Logout</button>

<%--<div class="container">--%>
    <header>
        <div class="profile-pic-container">
            <img class="profile-pic" src="https://s3.amazonaws.com/uifaces/faces/twitter/nuraika/128.jpg" alt="" />
            <div class="search-container"><i class="icon search"></i></div>
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

    <div class="col-md-4" ng-repeat="photo in profile.photoList">
        <div class="thumbnail" >
            <img src="uploads/{{photo.image_path}}" style="width:100%" ng-click="profile.commentModal(photo.image_path)">
            {{photo.caption}}
        </div>
    </div>


