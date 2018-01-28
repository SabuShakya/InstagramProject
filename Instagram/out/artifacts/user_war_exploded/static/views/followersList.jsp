<div class="modal-header">
    <h1 class="modal-title" id="modal-title">Followed By:</h1>
</div>

<div class="modal-body" id="modal-body">
    <div ng-repeat="follow in followersCtrl.followers">
        {{follow.userName}}
    </div>
</div>

<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="followersCtrl.ok()">Done</button>
</div>
