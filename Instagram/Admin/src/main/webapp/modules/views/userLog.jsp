<div class="modal-header">
    <div class="modal-title" id="modal-title">
        <h1>Users Log</h1>
    </div>
</div>

<div class="modal-body" id="modal-body">
    <h4>Total Users:{{userLog.totalUsers}}</h4>
    <h4>Total Active Users:{{userLog.activeUsers}}</h4>
    <h4>Total Uploads:{{userLog.totalUploads}}</h4>
    <h4>No of Uploads/day:{{userLog.uploadsPerDay}}</h4>
</div>

<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="userLog.close()">Close</button>

</div>