<div class="modal-header">
<h1 class="modal-title" id="modal-title">{{comment.userDisplayName}}</h1>
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
            <span class='save'></span></a>
        </div>

        <div ng-repeat="comment in comment.commentList">
            {{comment.username}} : {{comment.comments}}
        </div>

        <div class='comment-section'>
                <input type='text' id='cmnt' ng-model="comment.comments" placeholder='Add a comment...'>
                <button ng-click="comment.add()">Add</button>
        </div>

    </div>
</section>
</div>

<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="comment.cancel()">Cancel</button>
</div>