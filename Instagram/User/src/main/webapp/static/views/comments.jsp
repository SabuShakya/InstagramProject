<div class="modal-header">
    <%--<h1 class="modal-title" id="modal-title">{{comment.userDisplayName}}</h1>--%>
</div>

<div class="modal-body" id="modal-body">
    <section>
        <div align="center">
            <img src="uploads/{{photo}}" width="100%">
        </div>

        <div class='caption'>
            </a><span>{{caption}}</span>
        </div>
        <br>

        <div class='footer'>
            <div class='react'>
                {{comment.likeCount}}
                <button ng-click="comment.like()">
                    <span class='love'></span>
                </button>
                <button role='button' ng-click="comment.showComments()">
                    <span class='comment'></span>
                </button>
                <button ng-click="comment.showLikeList()">
                    <span class='save'></span></a>
                </button>
            </div>
            <div ng-show="comment.showLikes">
                <h5 style="color: #0089d8">Liked By:</h5>
                <div ng-repeat="like in comment.likes">
                    <span style="color: #449d44">{{like.userName}}</span>
                </div>
            </div>

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
    <button class="btn btn-warning" type="button" ng-click="comment.cancel()">Cancel</button>
</div>