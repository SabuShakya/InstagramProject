<div ng-include src="'static/views/navigation.html'"></div>

<h2> Blocked users list: </h2>
<div ng-repeat="list in block.blockList">
        <div class="col-md-8">
        <img class="img-responsive img-circle margin" src="/uploads/{{list.profilePhoto}}" width="10%" height="10%">

            <h4><a href="/#!/searchedUser" ng-click="block.openProfile(list)">{{list.blockedUsername}}</a></h4>
    </div>
</div>
</div>