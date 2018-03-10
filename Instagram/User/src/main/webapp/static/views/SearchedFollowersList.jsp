<div class="modal-header">
    <h3 class="modal-title" id="modal-title">Followed By:</h3>
</div>

<div class="modal-body" id="modal-body">
    <div ng-repeat="follow in followersCtrl.searchedFollowers">
        <div class="col-md-12">
            <img class="img-responsive img-circle margin" src="/uploads/{{follow.imagename}}" width="10%" height="10%">

            <h4><a href="/#!/searchedUser" ng-click="followersCtrl.openProfile(follow)">{{follow.username}}</a>
            </h4>
        </div>
        <div class="col-md-12" ng-hide="follow.showResultButtons" style="float: right">
            <button ng-click="followersCtrl.followUser(follow)">Follow</button>
        </div>
        <div class="col-md-12" ng-show="follow.showResultButtons" style="float: right">
            <button ng-click="followersCtrl.unfollowUser(follow)">Un-Follow</button>
        </div>
    </div>
</div>

<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="followersCtrl.ok()">Done</button>
</div>

