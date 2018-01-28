<div ng-include src="'modules/views/DashBoard.html'"></div>

    <div>
        <button class = "btn btn-pull center"><a href="#!/addAdmin">Add New Admin</a></button>
    </div>
        <br>
       <div>
           <button class = "btn btn-pull center"><a href="#!/manageAdmin">Manage Admins</a></button>
       </div>
    <br>
    <div>
        <button type="button" class="btn-default" ng-click="admin.openUserLog()">Show User Log</button>
    </div>
</div>