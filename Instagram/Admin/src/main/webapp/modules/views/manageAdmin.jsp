<div ng-include src="'modules/views/DashBoard.html'"></div>
<div>
    <h1> All users </h1>

    <div class="alert alert-success" ng-show="saved">
        <strong>{{message}}</strong>
    </div>
    <%--<table class="table table-striped">--%>
    <table ng-table="manage.tableParams" class="table table-bordered table-striped" show-filter="true">
        <%--<thead>--%>
        <%--<tr>--%>
        <%--<th>Index</th>--%>
        <%--<th>Name</th>--%>
        <%--<th>User Name</th>--%>
        <%--<th>Email</th>--%>
        <%--<th>Profile Picture</th>--%>
        <%--<th>Add Admin Picture</th>--%>
        <%--<th>Edit</th>--%>
        <%--<th>Delete</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <tr ng-repeat="ad in $data">
            <td data-title="'S.No'">{{$index+1}}</td>
            <td data-title="'Full Name'" filter="{ name: 'text'}" sortable="'name'">{{ad.name}}</td>
            <td data-title="'User Name'" filter="{ userName: 'text'}" sortable="'userName'">{{ad.userName}}</td>
            <td data-title="'Email'" filter="{ email: 'text'}" sortable="'email'">{{ad.email}}</td>
            <td data-title="'Admin Photo'"><img src="uploads/{{ad.image}}" width="50px" height="50px"></td>
            <%--<td >--%>
            <%--<button type="button" ng-click="manage.openPhotoModal(ad)" class="btn btn-info">AddPhoto</button>--%>
            <%--</td>--%>
            <td data-title="'Edit Admin'">
                <button type="button" ng-disabled="ad.disableEditDeleteButtons"
                        ng-click="manage.openEditModal(ad)" class="btn btn-info">
                    Edit
                </button>
            </td>
            <td data-title="'Delete Admin'">
                <button type="button" ng-disabled="ad.disableEditDeleteButtons"
                        ng-click="manage.openDeleteModal(ad)" class="btn btn-danger">
                    Delete
                </button>
            </td>
        </tr>
        <%--</tbody>--%>
    </table>
</div>