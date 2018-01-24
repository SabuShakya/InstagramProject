<div ng-include src="'static/views/navigation.html'"></div>
<section>
    <div align="center" ng-repeat="post in main.posts">
        <div class='insta fade-scroll'>
            <div class='top-insta'>
                <%--<a href="#" target='_blank'><img class="profile-pic" src='static/images/login.png'></a>--%>
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
                    <div ng-show="main.showList" ng-repeat="comment in main.commentList">
                        {{comment.username}} : {{comment.comments}}
                    </div>
                    <input type='text' id='cmnt' ng-model="post.comment" placeholder='Add a comment...'>
                    <button ng-click="main.addComment(post)">Add</button>
                    <span class='dot02'></span>
                </div>
            </div> <!-- end Footer -->
        </div>
    </div>
</section>
