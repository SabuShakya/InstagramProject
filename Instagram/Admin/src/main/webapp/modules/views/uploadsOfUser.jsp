<div ng-include src="'modules/views/DashBoard.html'"></div>
<link href="css/newsFeed.css">

<%--<section>--%>
    <%--<div align="center" ng-repeat="uploads in userUploads.uploadList">--%>
        <%--<div class='insta fade-scroll'>--%>
            <%--<div class='top-insta'>--%>
                <%--<img class="img-responsive img-circle margin" src="uploads/{{uploads.profilePic}}" width="10%"--%>
                     <%--height="10%">--%>
                <%--{{uploads.username}}--%>
                <%--<span class='dot'></span>--%>
            <%--</div>--%>
            <%--<div class='post'>--%>
                <%--<div class="overlay">--%>
                    <%--<span></span>--%>
                <%--</div>--%>
                <%--<div class="thumbnail principal-post">--%>
                    <%--<img src="uploads/{{uploads.image_path}}">--%>
                <%--</div>--%>
                <%--<div class='caption'>--%>
                    <%--</a><span>{{uploads.caption}}</span>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<label>Created On:--%>
                <%--<span class="date-of-post">{{uploads.created_date}}</span>--%>
            <%--</label>--%>

            <%--<div class='footer'>--%>
                <%--<div class='react'>--%>
                    <%--<h4>{{uploads.countOfLikes}}</h4>--%>
                    <%--<button role='button' ng-click="userUploads.showComments(uploads)">--%>
                        <%--<span class='comment'></span>--%>
                    <%--</button>--%>
                    <%--<div ng-show="userUploads.showing" ng-repeat="comment in userUploads.commentList">--%>
                        <%--<div ng-show="userUploads.showList" ng-repeat="comm in userUploads.commentList">--%>

                        <%--{{comment.username}} : {{comment.comments}}--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<button ng-click="userUploads.openLikeListModal(post)">--%>
                        <%--<span class='save'></span>--%>
                    <%--</button>--%>

                <%--</div>--%>
                <%--<br>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</section>--%>

<section>
    <div align="center" ng-repeat="uploads in userUploads.uploadList">
        <div class='insta fade-scroll'>
            <div class='top-insta'>
                <img class="img-responsive img-circle margin" src="uploads/{{uploads.profilePic}}" width="10%"
                     height="10%">
                {{uploads.username}}
            </div>
            <div class='post'>
                <div class="overlay">
                    <span></span>
                </div>
                <div class="thumbnail principal-post">
                    <img src="uploads/{{uploads.image_path}}">
                </div>
                <div class='caption'>
                    </a><span>{{uploads.caption}}</span>
                </div>
            </div>
            <label>Created On:
                <span class="date-of-post">{{uploads.created_date}}</span>
            </label>
            <div class='footer'>
                <div class='react'>
                    <%--{{uploads.countOfLikes}}--%>
                    <button>
                        <span> {{uploads.countOfLikes}}</span>
                    </button>
                    <button ng-click="userUploads.showComments(uploads)">
                        <span class='comment'></span>
                        <div ng-show="userUploads.showing" ng-repeat="comment in userUploads.commentList">
                            <div ng-show="userUploads.showList" ng-repeat="comm in userUploads.commentList">
                            {{comment.username}} : {{comment.comments}}
                            </div>
                        </div>
                    </button>
                        <button ng-click="userUploads.openLikeListModal(post)">
                            <span class='save'></span>
                        </button>
                </div>
            </div>
        </div>
    </div>
</section>

