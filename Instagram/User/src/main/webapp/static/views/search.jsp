<div ng-include src="'static/views/navigation.html'"></div>

<div align="center">
    <input type="text" placeholder="Enter username of person you want to search"
           ng-model="search.searchTerm" style="width: 50%"/><br><br>
    <button type="button" class="btn-default" ng-click="search.search()">Search</button> <br>  <br>
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

<%--<div class="large-padded-row">--%>
    <%--<h3>Search</h3>--%>
    <%--<div class="padded-row">--%>
        <%--<angucomplete--%>
                <%--id="ex2"--%>
                <%--placeholder="Search people"--%>
                <%--pause="300"--%>
                <%--selectedobject="search"--%>
                <%--localdata="searchResult"--%>
                <%--searchfields="search.searchTerm"--%>
                <%--titlefield="search.searchTerm"--%>
                <%--&lt;%&ndash;descriptionfield="twitter"&ndash;%&gt;--%>
                <%--&lt;%&ndash;imagefield="pic"&ndash;%&gt;--%>
                <%--minlength="1"--%>
                <%--inputclass="form-control form-control-small"--%>
                <%--matchclass="highlight" />--%>
    <%--</div>--%>
    <%--<div class="" ng-show="search.searchResult">--%>
        <%--You selected <span class="bold-span">{{search.originalObject.searchTerm}}</span>--%>
    <%--</div>--%>
<%--</div>--%>


