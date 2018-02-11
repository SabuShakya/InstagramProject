<div ng-include src="'modules/views/DashBoard.html'"></div>
<div ng-include src="'modules/views/breadcrumb.html'"></div>

<div align="center">
    <h1>{{usersList.title}}</h1>
</div>
<div class="container">
    <div ng-repeat="user in usersList.listOfUsers">
        <div class="row" style="display: flex">
            <div class="col-md-4" style="float: left">
                <img class="img-responsive img-circle margin" src="/uploads/{{user.imagename}}" width="10%"
                     height="10%">
            </div>
            <div class="col-md-4" style="float: left">
                <h4><a href="#!/adminPage/showClickedUserProfile" ng-click="usersList.openProfile(user)">{{user.username}}</a>
                </h4>
                <h5>{{user.fullname}}</h5>
            </div>
        </div>
    </div>
</div>
<ul uib-pagination boundary-links="true"
    total-items="usersList.totalItems"
    items-per-page="usersList.maxSize"
    ng-model="usersList.currentPage" class="pagination-sm" previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;"
    ng-change="usersList.pageChanged()"></ul>