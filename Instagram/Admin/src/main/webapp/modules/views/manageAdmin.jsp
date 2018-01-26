<div ng-include src="'modules/views/DashBoard.html'"></div>
<div>
    <h1> All users </h1>

    <div class="alert alert-success" ng-show="saved">
        <strong>{{message}}</strong>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Index</th>
            <th>Name</th>
            <th>User Name</th>
            <th>Email</th>
            <th>Profile Picture</th>
            <th>Add Admin Picture</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="ad in manage.adminList">
            <td>{{$index+1}}</td>
            <td>{{ad.name}}</td>
            <td>{{ad.userName}}</td>
            <td>{{ad.email}}</td>
            <td><img src="uploads/{{ad.image}}" width="50px" height="50px"></td>
            <td>
                <button type="button" ng-click="manage.openPhotoModal(ad)" class="btn btn-info">AddPhoto</button>
            </td>
            <td>
                <button type="button" ng-click="manage.openEditModal(ad)" class="btn btn-info">Edit</button>
            </td>
            <td>
                <button type="button" ng-click="manage.openDeleteModal(ad)" class="btn btn-danger">Delete</button>
            </td>
        </tr>
        </tbody>
    </table>
</div>