<div ng-include src="'static/views/navigation.html'"></div>

<div align="center">
    <input type="text" placeholder="Enter username of person you want to search"
           ng-model="search.searchTerm" style="width: 50%"/><br><br>
    <button type="button" class="btn-default" ng-click="search.search()">Search</button> <br>  <br>
</div>
<div ng-repeat="user in search.searchResult">

     <img class="img-responsive img-circle margin" src="/uploads/{{user.imagename}}" width="10%" height="10%">
    <h4><a href="/#!/searchedUser" ng-click="search.openProfile(user)">{{user.username}}</a></h4>
        <h5>{{user.fullname}}</h5>

    <%--<div ng-show="search.showResultButtons">--%>

    <div ng-if="search.showFollowBtn">
        <button type="button" class="btn-default" ng-click="search.followUser(user)">Follow</button>
    </div>

    <div ng-hide="search.showFollowBtn">
        <button type="button" class="btn-default" ng-click="search.unfollowUser(user)">Un-Follow</button>
    </div>
    <%--</div>--%>
</div>

<%--<div ng-include src="'static/views/navigation.html'"></div>--%>

<%--<div align="center">--%>
    <%--<input type="text" placeholder="Enter Username of person you want to search"--%>
           <%--ng-model="search.searchTerm" style="width: 50%"/><br><br>--%>
    <%--<button type="button" class="btn-default" ng-click="search.search()">Search</button>--%>
<%--</div>--%>
<%--<div ng-repeat="user in search.searchResult">--%>
    <%--<img src="/uploads/{{user.imagename}}" width="50px">--%>
    <%--<h4><a href="/#!/searchedUser" ng-click="search.openProfile(user)">{{user.username}}</a></h4><br>--%>
    <%--<h5>{{user.fullname}}</h5>--%>
<%--</div>--%>