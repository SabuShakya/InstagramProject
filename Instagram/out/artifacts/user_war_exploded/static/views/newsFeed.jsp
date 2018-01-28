<div ng-include src="'static/views/navigation.html'"></div>
<section>
    <div align="center" ng-repeat="post in main.posts">
        <div class='insta fade-scroll'>
            <div class='top-insta'>
                <a href='#' target='_blank' class='user'>{{post.username}}
                </a>
                <span class='dot'></span>
            </div>
            <div class='post'>
                <div class="overlay">
                    <span></span>
                </div>
                <div class='caption'>
                    </a><span>{{post.caption}}</span>
                </div>

                <div class="thumbnail principal-post">
                    <img src="/uploads/{{post.image_path}}">
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

                <div class='comment-section'>
                    <div ng-show="main.showList" ng-repeat="comm in main.commentList">
                        {{comm.username}} : {{comm.comments}}
                        <br>
                        <br>
                        <button type="button" class="btn btn-danger" ng-click="main.openDeleteModal(comm)"> Delete
                        </button>
                        <button type="button" class="btn btn-success" ng-click="main.openEditModal(comm)">Edit</button>
                    </div>

                    <div ng-hide ="main.showCommentList">
                        <input type='text' id='comment' ng-model="clickedComment.comments" placeholder='Edit comment...'>
                        <button ng-click="main.edit()">Edit</button>
                    </div>

                    <input type='text' id='cmnt' ng-model="post.comment" placeholder='Add a comment...'>
                    <button ng-click="main.addComment(post)">Add</button>
                    <br>
                </div>
        </div>
    </div>
    </div>
</section>

<%--<h4>Default</h4>--%>
<ul uib-pagination total-items="totalItems" ng-model="currentPage" ng-change="pageChanged()"></ul>
<ul uib-pagination boundary-links="true" total-items="totalItems" ng-model="currentPage" class="pagination-sm" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"></ul>
<ul uib-pagination direction-links="false" boundary-links="true" total-items="totalItems" ng-model="currentPage"></ul>
<ul uib-pagination direction-links="false" total-items="totalItems" ng-model="currentPage" num-pages="smallnumPages"></ul>
<pre>The selected page no: {{main.currentPage}}</pre>
<%--<button type="button" class="btn btn-info" ng-click="setPage(3)">Set current page to: 3</button>--%>

<%--<hr />--%>