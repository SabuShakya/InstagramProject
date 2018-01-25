<div class="modal-header">
    <div class="modal-title" id="modal-title">
        <h1>Users Log</h1>
    </div>
</div>

<div class="modal-body" id="modal-body">
    <h4>Total Users:</h4>{{userLog.totalUsers}}
    <h4>Total Active Users:</h4>{{userLog.activeUsers}}
    <h4>Total Uploads:</h4>{{userLog.totalUploads}}
    <h4>No of Uploads/day:</h4>{{userLog.uploadsPerDay}}
</div>

<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="userLog.close()">Close</button>

</div>