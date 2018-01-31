<div class="modal-header">
    <div class="modal-title" id="modal-title">
        <h1>Add Photo</h1>
    </div>
</div>

<div class="modal-body" id="modal-body">
    <form name="myForm" class="form-horizontal" novalidate>
        <input type='file' ng-model='modalController.imageName' base-sixty-four-input>
        <img src="data:image/jpeg;base64,{{modalController.imageName.base64}}" width="50%" height="50%">
    </form>
    <span style="color:red" ng-show="modalController.show_error_msg">Error Occurred try again</span>
</div>

<div class="modal-footer">
    <button class="btn btn-default" type="button" ng-disabled="modalController.clickButton" ng-click="modalController.updatePhoto()">Update</button>
    <button class="btn btn-warning" type="button" ng-click="modalController.cancel()">Cancel</button>
</div>