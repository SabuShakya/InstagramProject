<div class="modal-header">
    <div class="modal-title" id="modal-title">
        <h1>Users Log</h1>
    </div>
</div>

<div class="modal-body" id="modal-body">
    <h4>Total Users:</h4>{{userLog.totalUsers}}
    <h4>Total Active Users:</h4>{{userLog.activeUsers}}
    <h4>Total Uploads:</h4>{{}}
    <h4>No of Uploads/day:</h4>{{}}
</div>

<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="modalController.cancel()">Close</button>

</div>