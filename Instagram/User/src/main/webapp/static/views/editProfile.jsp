<div class="modal-header">
    <h3 class="modal-title" id="modal-title">Edit Profile</h3>
</div>
<div class="modal-body" id="modal-body">

    <form>
        <input type='file' ng-model='editProfile.profilePic' base-sixty-four-input>
    </form>

    <img src="data:image/jpeg;base64,{{editProfile.profilePic.base64}}" width ="50%" height="50%">
</div>

<div class="modal-footer">
    <button class="btn btn-primary" type="button" ng-click="editProfile.uploadPhoto()">Edit profile</button>
    <button class="btn btn-warning" type="button" ng-click="editProfile.close()">Cancel</button>
</div>
