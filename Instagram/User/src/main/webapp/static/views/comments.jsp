<div class="modal-header">
    <%--<h3 class="modal-title" id="modal-title">Upload Photo</h3>--%>
</div>
<div class="modal-body" id="modal-body">
    <%--<div class="thumbnail" ng-repeat="photo in profile.photoList" >--%>
        <%--<img src="uploads/{{photo.image_path}}" style="width:100%">--%>
        <%--{{photo.caption}}--%>
    <%--</div>--%>
    <img src="uploads/{{photo}}" width="100%">

    <div class="container">
        <form name="myForm" novalidate>
            <h4>Add Comment</h4>
            <textarea id="subject" name="subject" ng-model="comment.comments" placeholder="Write something.." cols="60" rows="10" required></textarea> <br>
            <br>
            <button class="btn btn-success" ng-click="comment.add()">Add Comment </button>
        </form>
    </div>

        <div ng-repeat="comment in comment.commentList">
            {{comment.comments}}
        </div>


</div>

<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="comment.close()">Cancel</button>
</div>