<div ng-include src="'modules/views/DashBoard.html'"></div>
<div ng-include src="'modules/views/breadcrumb.html'"></div>

    <div>
        <button class = "btn btn-pull center"><a href="#!/adminPage/addAdmin">Add New Admin</a></button>
    </div>
        <br>
       <div>
           <button class = "btn btn-pull center"><a href="#!/adminPage/manageAdmin">Manage Admins</a></button>
       </div>
    <br>
    <%--<div>--%>
        <%--<button type="button" class="btn-default" ng-click="admin.openUserLog()">Show User Log</button>--%>
    <%--</div>--%>
</div>
<div class="col col-md-9">
    <div class="row" class="container-fluid" >
            <div class="col col-md-5">
                <h4>Users Stats:</h4>
                <a href="#!/adminPage/usersList">TotalUsers:<span class="pull-right strong">{{admin.totalUsers}}</span></a>
                <div class="progress">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="{{admin.totalUsers}}"aria-valuemin="0" aria-valuemax="100" style="width:45%">{{admin.totalUsers}}%</div>
                </div>

                <a href="#!/adminPage/activeUsersList">Active Users:<span class="pull-right strong">{{admin.activeUsers}}</span></a>
                <div class="progress">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="{{admin.activeUsers}}"aria-valuemin="0" aria-valuemax="100" style="width:57%">{{admin.activeUsers}}%</div>
                </div>

                <a href="#!/adminPage/totalUploads">Total Uploads:<span class="pull-right strong">{{admin.totalUploads}}</span></a>
                <div class="progress">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="{{admin.totalUploads}}"aria-valuemin="0" aria-valuemax="100" style="width:25%">{{admin.totalUploads}}%</div>
                </div>
                <a href="#!/adminPage/totalUploadsPerDay">Total Uploads/day:<span class="pull-right strong">{{admin.uploadsPerDay}}</span></a>
                <div class="progress">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="{{admin.uploadsPerDay}}"aria-valuemin="0" aria-valuemax="100" style="width:25%">{{admin.uploadsPerDay}}%</div>
                </div>
            </div>
        </div>
</div>
    </div>