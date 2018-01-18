<div ng-include src="'static/views/navigation.html'"></div>

<div align="center">
    <input type="text" placeholder="Enter Username of peron you want to search"
           ng-model="search.searchTerm" style="width: 50%"/><br><br>
    <button type="button" class="btn-default" ng-click="search.search()">Search</button>
</div>
<div ng-repeat="user in search.searchResult">
    <%--<img src="/uploads/+user.imagename" width="50px">--%>
        <h4><a href="/#!/searchedUser" ng-click="search.openProfile(user)">{{user.username}}</a></h4><br>
        <h5>{{user.fullname}}</h5>
</div>