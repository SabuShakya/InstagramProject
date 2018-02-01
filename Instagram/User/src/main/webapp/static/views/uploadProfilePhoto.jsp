<div class="modal-header">
    <h3 class="modal-title" id="modal-title">Upload profile picture</h3>
</div>
<div class="modal-body" id="modal-body">

    <form>
        <input type='file' ng-model='profilePhoto.imageName' base-sixty-four-input>
    </form>

    <img src="data:image/jpeg;base64,{{profilePhoto.imageName.base64}}" width ="50%" height="50%">
</div>

<div class="modal-footer">
    <button class="btn btn-primary" type="button" ng-click="profilePhoto.uploadPhoto()" ng-disabled="profilePhoto.submitClicked">Upload</button>
    <button class="btn btn-warning" type="button" ng-click="profilePhoto.close()">Cancel</button>
</div>
