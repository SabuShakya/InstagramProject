<div class="modal-header">
    <h3 class="modal-title" id="modal-title">Followed By:</h3>
</div>
<div class="modal-body" id="modal-body">
    <div ng-repeat="follow in followersCtrl.followers">
            <img class="img-responsive img-circle margin" src="/uploads/{{follow.imagename}}" width="10%" height="10%">
            {{follow.username}}
    </div>
</div>
<div class="modal-footer">
    <button class="btn btn-warning" ng-click="followersCtrl.ok()">OK</button>
</div>



