<div class="modal-header">
    <h3 class="modal-title" id="modal-title">Following:</h3>
</div>

<div class="modal-body" id="modal-body">
    <div ng-repeat="follow in followingCtrl.searchedFollowing">
        <div class="col-md-12">
            <img class="img-responsive img-circle margin" src="/uploads/{{follow.imagename}}" width="10%" height="10%">
            {{follow.username}}
        </div>
    </div>
</div>

<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="followingCtrl.ok()">Done</button>
</div>

