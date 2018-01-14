<div ng-show="admin.showAll">
    <h1>DashBoard</h1>
    <div align="right">
        <h4 align="right">{{admin.adminId}}</h4>
        <button class="btn btn-danger" style="width: 20%" ng-click="admin.logout()">Logout</button>
    </div>
    <div>
        <button><a href="/#!/addAdmin">Add New Admin</a></button>
        <button ng-show="admin.showList" ng-click="admin.showAdminList()">Manage Admins</button>
    </div>
    <div>
        <button type="button" class="btn-default" ng-click="admin.openUserLog()">Show User Log</button>
    </div>
    <div ng-hide="admin.showList">
        <h1> All users </h1>
        <div align="right">
            <button class="btn btn-default" style="width: 20%" ng-click="admin.refreshList()">Refresh List</button>
        </div>
        <div class="alert alert-success" ng-show="saved">
            <strong>{{message}}</strong>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Index</th>
                <th>Name</th>
                <th>UserId</th>
                <th>Email</th>
                <th>Profile Picture</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="ad in admin.adminList">
                <td>{{$index+1}}</td>
                <td>{{ad.name}}</td>
                <td>{{ad.userId}}</td>
                <td>{{ad.email}}</td>
                <td><img src="uploads/{{ad.image}}" width="50px" height="50px"></td>
                <td>
                    <button type="button" ng-click="admin.openEditModal(ad)" class="btn btn-info"> Edit</button>
                </td>
                <td>
                    <button type="button" ng-click="admin.openDeleteModal(ad)" class="btn btn-danger"> Delete</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>