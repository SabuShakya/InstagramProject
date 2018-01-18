<div class="modal-header">
    <h1 class="modal-title" id="modal-title">People who liked this...</h1>
</div>

<div class="modal-body" id="modal-body">
    <div ng-repeat="like in likes.likeList">
        {{like.username}}
    </div>

</div>

<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="comment.cancel()">Cancel</button>
</div>