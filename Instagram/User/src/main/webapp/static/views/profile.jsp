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
        <p class="description">Deep you whales to form appear fifth beginning. Fourth give said he Forth.</p>

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
            <div ng-repeat="photo in profile.photoList">--%>
                <tr>
                <td><img src="uploads/{{photo.image_path}}"></td>
                <td>{{photo.caption}}</td>
                <td>{{photo.likes}}</td>
                <td>{{photo.comments}}</td>
                </tr>
            </div>
    </div>
</div>

<style>
/*white: #fff;*/
/*black: #202020;*/
/*grey: #808080;*/
/*$background: #c3c8c9;*/
/*$pink: #FF6C8B;*/
/*$orange: #FFAC40;*/
/*$default-border: 1px background solid;*/

 /*Basics*/
body {
/*background-color: background;*/
font-family: 'Roboto Condensed';
}

.container {
margin: 0 auto;
background-color: white;
/*max-width: 400px;*/
/*max-height: 650px;*/
overflow: hidden;
/*box-shadow: 0px 0px 20px black;*/
/*margin-top: 1em;*/
color: black;
}

/*// Icons*/
a {
color: black;
}

.icon::after {
font-family: 'FontAwesome';
font-style: normal;
font-size: 1.5em;
color: white;
}

.icon.burger::after {
content: '\f0c9';
float: left;
}

.icon.edit::after {
content: '\f040';
float: right;
}

.icon.search::after {
content: '\f002';
}

.icon.grid::after {
content: '\f00a';
}

.icon.list::after {
content: '\f03a';
}

/*// Header*/
header {
background: url(https://unsplash.it/1080/720?image=601) no-repeat;
background-size: cover;
position: relative;
min-height: 230px;
margin-bottom: 40px;
}

.nav-bar {
padding: 1.5em 0.5em 0.5em;
color: white;
text-align: center;
}

.name {
font-size: 1.2em;
}

.profile-pic-container {
bottom: -40px;
left: calc(50% - 40px);
position: absolute;

}

.search-container {
background-color: pink;
border-radius: 50%;
width: 15px;
height: 15px;
position: absolute;
bottom: 1em;
right: 0;}

i::after{
width: 100%;
text-align: center;
font-size: 10px;
position: absolute;
top: 50%;
margin-top: -5px;
}


.profile-pic {
max-width: 80px;
border-radius: 50%;
border: 2px solid white;
box-shadow: 0 0 15px black;
}

.main{
}

.description{
padding: 0.5em 2em;
text-align: center;
}

.stats-container{
padding-bottom: 0.5em;
border-bottom: 1px background solid;
}

.stat{
display: inline-block;
width: 24%;
text-align: center;
border-right: 1px background solid;
}

.text{
color: grey;
font-weight: 100;
font-size: 0.8em;
text-transform: uppercase;
}

.view-options{
border-bottom: 1px background solid;
}

.view-option {
    display: inline-block;
    width: 49%;
    text-align: center;
    border-left: 1px background solid;
    margin: 0.8em 0;
}

.pictures {
display: flex;
flex-wrap: wrap;
justify-content: space-between;
}

.picture{
width:33%;
height: 33%;
margin-bottom: 2px;
}


</style>