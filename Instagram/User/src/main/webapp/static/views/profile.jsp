<link rel="stylesheet" type="text/css" href="static/css/profile.css">
<button ng-click="profile.openModal()" class="btn btn-success btn-lg pull-right"> Upload Photo</button>
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
    <button class="btn btn-danger" ng-click="profile.logout()">Logout</button>

<div class="container">
    <header>
        <div class="nav-bar">
            <a href="#"><i class="icon burger"></i></a>
            <span class="name">Angela Boyd</span>
            <a href="#"><i class="icon edit"></i></a>
        </div>
        <div class="profile-pic-container">
            <img class="profile-pic" src="https://s3.amazonaws.com/uifaces/faces/twitter/nuraika/128.jpg" alt="" />
            <div class="search-container"><i class="icon search"></i></div>
        </div>
    </header>
    <div class="main">
        <p class="description">welcome</p>

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

        <%--<div class="pictures">--%>
            <div ng-repeat="photo in profile.photoList">
                <tr>
                <td><img src="upload/{{photo.image_path}}"></td>
                <td>{{photo.caption}}</td>
                <td><button class= "btn btn-primary" ng-click="profile.edit(photo)"></button></td>
                <%--<td>{{photo.likes}}</td>--%>
                <%--<td>{{photo.comments}}</td>--%>
                </tr>
            </div>
    </div>
</div>

