<div ng-include src="'static/views/navigation.html'"></div>
<section>
    <div infinite-scroll="main.getPosts()" infinite-scroll-distance='2'>
    <div align="center" ng-repeat="post in main.posts">
        <div class='insta fade-scroll'>
            <div class='top-insta'>
                <img class="img-responsive img-circle margin" src="/uploads/{{post.profilePic}}" width="10%" height="10%">
                {{post.username}}

            </div>
            <div class='post'>
                <div class="overlay">
                    <span></span>
                </div>
                <div class="thumbnail principal-post">
                    <img src="/uploads/{{post.image_path}}">
                </div>
                <div class='caption'>
                    </a><span>{{post.caption}}</span>
                </div>
            </div>
            <label>Created On:
                <span class="date-of-post">{{post.created_date}}</span>
            </label>
            <div class='footer'>

                <div class='react'>
                    {{post.countOfLikes}}
                    <button ng-click="main.like(post)">
                        <span class='love'></span>
                    </button>
                    <button role='button' ng-click="main.showComments(post)">
                        <span class='comment'></span>
                    </button>

                    <button ng-click="main.openLikeListModal(post)">
                        <span class='save'></span>
                    </button>

                </div>
                <br>

                <%--<div class='comment-section'>--%>
                    <%--&lt;%&ndash;<div ng-show="main.showList" ng-repeat="comm in main.commentList">&ndash;%&gt;--%>
                    <%--<div ng-show="main.showList" ng-repeat="comm in post.comments">--%>
                        <%--{{comm.username}} : {{comm.comments}}--%>
                        <%--<br>--%>
                        <%--<br>--%>
                        <%--<div ng-show="comm.showCommentButtons">--%>
                            <%--<button type="button" class="btn btn-success" ng-click="main.openEditModal(comm)">Edit</button>--%>
                        <%--</div>--%>
                        <%--<button type="button" class="btn btn-danger" ng-click="main.openDeleteModal(comm)"> Delete--%>
                        <%--</button>--%>
                    <%--</div>--%>

                    <%--<div ng-hide ="main.showCommentList">--%>
                        <%--<input type='text' id='comment' ng-model="clickedComment.comments" placeholder='Edit comment...'>--%>
                        <%--<button ng-click="main.edit()">Edit</button>--%>
                    <%--</div>--%>

                    <%--<input type='text' id='cmnt' ng-model="post.comment" placeholder='Add a comment...'>--%>
                    <%--<button ng-click="main.addComment(post)">Add</button>--%>
                    <%--<br>--%>
                <%--</div>--%>
        </div>
        </div>
    </div>
    </div>
</section>

<ul uib-pagination boundary-links="true"
    total-items="main.totalItems"
    items-per-page="main.maxSize"
    ng-model="main.currentPage" class="pagination-sm" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
    ng-change="main.pageChanged()"></ul>
