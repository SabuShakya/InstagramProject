<div ng-include src="'static/views/navigation.html'"></div>

<div align="center">
    <%--<input type="text" placeholder="Enter username of person you want to search"--%>
           <%--ng-model="search.searchTerm" style="width: 50%"/><br><br>--%>

        <div class="large-padded-row">
            <h3>Enter username of person you want to search </h3>
            <div class="padded-row">
                <%--{{search.selectedSearchObject}}--%>
                <div angucomplete
                     id="ex5"
                     placeholder="Search users"
                     pause="300"
                     minlength="1"
                     ng-model="search.searchTerm"
                     selectedobject="search.selectedSearchObject"
                     url="http://localhost:8080/anguSearch/"
                     titlefield="username"
                     input-class="form-control form-control-small">
                </div><br><br>
    <button type="button" class="btn-default" ng-click="search.search()">Search</button>
            </div>
        </div>
</div>

<div ng-show="search.showList">
<div ng-repeat="user in search.finalList">

     <img class="img-responsive img-circle margin" src="/uploads/{{user.imagename}}" width="10%" height="10%">
    <h4><a href="/#!/searchedUser" ng-click="search.openProfile(user)">{{user.username}}</a></h4>
        <h5>{{user.fullname}}</h5>

    <div ng-hide="user.showResultButtons">
        <button type="button" class="btn-default" ng-click="search.followUser(user)">Follow</button>
    </div>

    <div ng-show="user.showResultButtons">
        <button type="button" class="btn-default" ng-click="search.unfollowUser(user)">Un-Follow</button>
    </div>
    </div>
</div>

<span style="color:red" ng-show="search.showMessage">No results found!</span>

</div>
</div>


