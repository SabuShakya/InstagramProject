<div class="modal-header">
    <h3 class="modal-title" id="modal-title">Liked By:</h3>
</div>

<div class="modal-body" id="modal-body">
    <div ng-repeat="like in likesctrl.likes">
        {{like.userName}}
    </div>
</div>
<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="likesctrl.ok()">OK</button>
</div>