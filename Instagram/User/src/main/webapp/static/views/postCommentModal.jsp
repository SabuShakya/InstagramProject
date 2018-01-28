<div class="modal-body" id="modal-body">
    <div ng-show="comment.showList">
        <div ng-repeat="commen in comment.commentList">
            {{commen.username}} : {{commen.comments}}
            <br>
            <div ng-show="{{commen.showCommentButtons}}">
                <button type="button" class="btn-sm btn-success" ng-click="comment.openEditModal(commen)">
                    Edit
                </button>
                <button type="button" class="btn-sm btn-danger" ng-click="comment.openDeleteModal(commen)">
                    Delete
                </button>
            </div>
        </div>
    </div>

    <div class='comment-section'>
        <input type='text' id='cmnt' ng-model="comment.comments" placeholder='Add a comment...'>
        <button ng-click="comment.add()">Add</button>
    </div>
    <br>

    <div ng-hide="comment.showCommentList">
        <input type='text' id='comment' ng-model="clickedComment.comments" placeholder='Edit comment...'>
        <button ng-click="comment.edit()">Edit</button>
    </div>
</div>
</section>
</div>

<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="comment.cancel()">Done</button>
</div>