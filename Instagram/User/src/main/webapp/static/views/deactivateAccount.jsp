<div ng-include src="'static/views/navigation.html'"></div>
<h1> Temporarily Disable Your Account</h1>
<br>
<h2>Hi {{update.userDisplayName}},</h2>
You can disable your account instead of deleting it. This means your account will be hidden until you reactivate it by logging back in.
<br><br>

<h2>For security, re-enter your password: </h2>
<div>
<form class="form-inline" name="myForm" novalidate>
    <div class="form-group">
        <label for="pwd">Password:</label>
        <input type="password" class="form-control" id="pwd" ng-model="update.password" placeholder="Enter password" name="pwd"required/><br>
        <span style="color:red" ng-show="myForm.pwd.$dirty && myForm.pwd.$invalid">
            <span ng-show="myForm.pwd.$error.required">Password required</span>
        </span>
        <br>
    </div>
    <br>
<input type="submit" value="Submit" ng-disabled="myForm.$invalid" ng-click="update.checkPassword()" ng-disabled="update.submitClicked"/>

<div ng-hide ="update.valid">
    <span style="color:red">{{update.error_msg}}</span>
</div>
</form>
</div>

<div ng-show="update.showDeactivateBtn">
When you press the button below, your photos, comments and likes will be hidden until you reactivate your account by logging back in.
    <br>
    <button type="button" class="btn btn-success" ng-click="update.deActivateAccount()">Temporarily disable account</button>
</div>
