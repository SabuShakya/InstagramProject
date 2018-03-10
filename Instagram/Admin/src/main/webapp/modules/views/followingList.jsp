<div class="modal-header">
    <h3 class="modal-title" id="modal-title">Following list :</h3>
</div>

<div class="modal-body" id="modal-body">
    <div ng-repeat="follow in followingCtrl.following">
        <img class="img-responsive img-circle margin" src="/uploads/{{follow.imagename}}" width="10%" height="10%">
            {{follow.username}}
    </div>
</div>
<br>
<div class="modal-footer">
    <button class="btn btn-warning" ng-click="followingCtrl.ok()">OK</button>
</div>
