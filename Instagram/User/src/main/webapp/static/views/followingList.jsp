<div class="modal-header">
    <h1 class="modal-title" id="modal-title">Following list :</h1>
</div>

<div class="modal-body" id="modal-body">
    <div ng-repeat="follow in followingCtrl.following">
        <div class="row" style="display:flex">
            <div class="col-md-12" style="float: left">
                <img class="img-responsive img-circle margin" src="/uploads/{{follow.imagename}}" width="10%"
                     height="10%">
                <h4><a href="/#!/searchedUser" ng-click="followingCtrl.openProfile(follow)">{{follow.username}}</a></h4>
            </div>
            <div ng-hide="follow.hideButtons">
                <div ng-hide="follow.showResultButtons" class="col-md-12" style="float:right">
                    <button ng-click="followingCtrl.followUser(follow)">Follow</button>
                </div>
                <div ng-show="follow.showResultButtons" class="col-md-12" style="float:right">
                    <button ng-click="followingCtrl.unfollowUser(follow)">Un-Follow</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="followingCtrl.ok()">Done</button>
</div>
