<div ng-include src="'static/views/navigation.html'"></div>
<section class = "section-content">
    <div infinite-scroll="main.pageChanged()" ng-model="main.currentPage" infinite-scroll-distance="1"
         infinite-scroll-immediate-check="false" infinite-scroll-container='".constrained"'
         infinite-scroll-parent="true" >`

        <div align="center" ng-repeat="post in main.finalPostList">
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

