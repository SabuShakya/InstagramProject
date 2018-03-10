<div ng-include src="'modules/views/DashBoard.html'"></div>
<div ng-include src="'modules/views/breadcrumb.html'"></div>

<link href="css/newsFeed.css">

<div infinite-scroll="userUploads.pageChanged()" ng-model="userUploads.currentPage" infinite-scroll-distance="2">
    <section>
        <div align="center" ng-repeat="uploads in userUploads.uploadList">
            <div class='insta fade-scroll'>
                <div class='top-insta'>
                    <img class="img-responsive img-circle margin" src="uploads/{{uploads.profilePic}}" width="10%"
                         height="10%">
                    {{uploads.username}}
                </div>
                <div class='post'>
                    <div class="thumbnail principal-post">
                        <img src="uploads/{{uploads.image_path}}">
                    </div>
                    <div class='caption'>
                        <span>{{uploads.caption}}</span>
                    </div>
                </div>

                <label>Created On:
                    <span class="date-of-post">{{uploads.created_date}}</span>
                </label>

                <div class='footer'>
                    <div class='react'>
                        <div class="btn-group btn-group-sm" role="group" aria-label="Small button group">
                            Likes:{{uploads.countOfLikes}}
                            <br>
                            <button type="button" class="btn btn-secondary"
                                    ng-click="userUploads.showComments(uploads)">
                                <span class='comment'></span>
                            </button>

                            <button type="button" class="btn btn-secondary"
                                    ng-click="userUploads.openLikeListModal(uploads)">
                                <span class='save'></span>
                            </button>
                        </div><br>
                        <div ng-show="userUploads.showing" ng-repeat="comm in uploads.comments">
                            {{comm.userName}} : {{comm.comments}}
                        </div>
                        <br>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

