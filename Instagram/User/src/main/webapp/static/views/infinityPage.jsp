<div ng-include src="'static/views/navigation.html'"></div>
<section class = "section-content">
    <div align="center" ng-show="main.showFollowMessage" style="color: rebeccapurple">
        <h2>{{main.message}}</h2>
    </div>
    <div infinite-scroll="main.pageChanged()" ng-model="main.currentPage" infinite-scroll-distance="1"
         infinite-scroll-immediate-check="false" infinite-scroll-container='".constrained"'
         infinite-scroll-parent="true" >

        <div ng-show="main.showFollowMesssage">
            <h3>Follow others to see their posts..</h3>
        </div>

        <div align="center" ng-repeat="post in main.finalPostList">
            <div class='insta fade-scroll'>
                <div class='top-insta'>
                    <img class="img-responsive img-circle margin" src="/uploads/{{post.profilePic}}" width="10%" height="10%">
                    <h4><a href="/#!/searchedUser" ng-click="main.openProfile(post)"> {{post.username}}</a>
                    </h4>

                </div>
                <div class='post'>

                    <div ng-show="main.showLoveIcon">
                    <div class="overlay">
                        <span></span>
                    </div>
                    </div>

                    <div class="thumbnail principal-post">
                        <img src="/uploads/{{post.image_path}}" ng-dblclick="main.like(post)">
                    </div>
                    <div class='caption'>
                        </a><span>{{post.caption}}</span>
                    </div>
                </div>
                <label>Posted On:
                    <span class="date-of-post">{{post.created_date}}</span>
                </label>
                <div class='footer'>

                    <div class='react'>
                        {{post.countOfLikes}}
                        <button ng-class="{'active':post.showRedButton}" ng-click="main.like(post)">
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
                </div>
            </div>
        </div>
        </div>
    </div>
</section>
</div>

<style type="text/css">
    .active{
        background:red;
    }
</style>
