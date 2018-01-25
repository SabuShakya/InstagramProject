<div class="modal-header">
    <h1 class="modal-title" id="modal-title">Following list :</h1>
</div>

<div class="modal-body" id="modal-body">
    <div ng-repeat="follow in followingCtrl.following">
        {{follow.following_userName}}
    </div>
</div>

<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="followingCtrl.ok()">Done</button>
</div>
