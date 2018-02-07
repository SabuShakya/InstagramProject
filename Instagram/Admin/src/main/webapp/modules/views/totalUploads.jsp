<div ng-include src="'modules/views/DashBoard.html'"></div>
<div ng-include src="'modules/views/breadcrumb.html'"></div>

<h1>{{totalUploads.title}}</h1>
<table ng-table="totalUploads.tableParams" class="table table-condensed table-bordered table-striped"
       show-filter="false">
    <tr ng-repeat="user in $data">
        <td data-title="'S.No'">{{$index+1}}</td>
        <td data-title="'Full Name'">
            <div class="row" style="display: flex">
                <div class="col-md-8">
                    <img src="uploads/{{user.profilePhoto}}" class="img-responsive img-circle margin"
                         width="40px" height="40px">
                </div>
                <div class="col-md-pull-8">
                    {{user.fullName}}
                </div>
            </div>
        </td>
        <td data-title="'User Name'">{{user.userName}}</td>
        <td data-title="'Users TotalUploads'">{{user.totalUploads}}</td>
        <td><a href="#!/adminPage/totalUploads/showUploadsOfUser" ng-click="totalUploads.showUploadsOfUser(user)">
            Show Uploads
        </a>
        </td>
    </tr>
</table>