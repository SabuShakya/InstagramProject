<div class="modal-header">
    <h1 class="modal-title" id="modal-title">Followed By:</h1>
</div>

<div class="modal-body" id="modal-body">
    <div ng-repeat="follow in followersCtrl.followers">
        <div class="col-md-12">
            <img class="img-responsive img-circle margin" src="/uploads/{{follow.image}}" width="10%" height="10%">

            <h4><a href="/#!/searchedUser" ng-click="followersCtrl.openProfile(follow)">{{follow.following_userName}}</a></h4>
        </div>
    </div>
</div>

<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="followersCtrl.ok()">Done</button>
</div>
