<div class="modal-header">
    <h3 class="modal-title" id="modal-title">Upload Photo</h3>
</div>
<div class="modal-body" id="modal-body">

<form>
    <input type='file' ng-model='img.imageName' base-sixty-four-input>
    <br>
    <input type = "text" name="caption" ng-model ="img.caption" placeholder="Write a caption...">

</form>

    <img src="data:image/jpeg;base64,{{img.imageName.base64}}" width ="100px" height="100px">
</div>

<div class="modal-footer">
    <button class="btn btn-primary" type="button" ng-click="img.uploadPhoto()">Upload photo</button>
    <button class="btn btn-warning" type="button" ng-click="img.close()">Cancel</button>
</div>