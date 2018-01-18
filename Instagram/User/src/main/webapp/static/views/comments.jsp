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
            <button type="button" class="btn btn-default btn-lg"  ng-click="comment.saveLike()"></button>
                    <%--"temp.likes = temp.likes+1">--%>
                <span class='love'></span>{{temp.likes}}</button>
            <span class='comment'></span></a>
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