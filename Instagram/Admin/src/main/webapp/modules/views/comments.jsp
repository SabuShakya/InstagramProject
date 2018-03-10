<div class="modal-header">
    <div align="right">
        <button type="button" class="btn btn-link" ng-click="comment.cancel()">X</button>
    </div>
    <div class="modal-body" id="modal-body">
        <div align="center">
            <img src="uploads/{{photo}}" width="80%" height="60%">
        </div>

        <div>
            <h4>{{comment.searchedUsername}}: {{caption}}</h4>
        </div>

        <div class='footer'>
            <div class='react'>
                <div class="btn-group btn-group-sm" role="group" aria-label="Small button group">
                    Likes: {{comment.likeCount}}
                    <br>
                    <button type="button" class="btn btn-secondary" ng-click="comment.showComments()">
                        <span class='comment'></span>
                    </button>
                    <button type="button" class="btn btn-secondary" ng-click="comment.showLikeList()">
                        <span class='save'></span></a>
                    </button>
                </div>
            </div>

            <div ng-show="comment.showLikes">
                <h5 style="color: #0089d8">Liked By:</h5>
                <div ng-repeat="like in comment.likeList">
                    <span style="color: #449d44">{{like.userName}}</span>
                </div>
            </div>

            <div ng-show="comment.showList">
                <div ng-repeat="commen in comment.commentList">
                    {{commen.username}} : {{commen.comments}}
                    <br>
                </div>
            </div>
            <br>
        </div>
    </div>
</div>

<%--<div class="modal-footer">--%>
<%--<button type="button" class="btn btn-warning" ng-click="comment.cancel()">OK</button>--%>
<%--</div>--%>

<style type="text/css">
    .active {
        background: red;
    }
</style>