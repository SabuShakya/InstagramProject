
<div class="modal-header">
    <div class="modal-title" id="modal-title">
        <h1>Delete</h1>
    </div>
</div>
<div class="modal-body" id="modal-body">
    <h3>Confirm Delete {{clickedAdmin.userId}}??</h3>
</div>
<div class="modal-footer">
    <button class="btn btn-danger" type="button" ng-click="modalController.deleteAdmin()">Delete</button>
    <button class="btn btn-warning" type="button" ng-click="modalController.cancel()">Cancel</button>
</div>