<div ng-include src="'static/views/navigation.html'"></div>
<h2 class="text-center">Update Password Here!!</h2>
<br>
<form name="editform" novalidate>
    <div class="container" align="center">
        <p>
           Your Username:
            <input type="text" name="username" ng-model="update.username" placeholder="Username" required/>
            <span style="color:red" ng-show="editform.username.$dirty && editform.username.$invalid">
            <span class="error" ng-show="editform.username.$error.required"> Username is required</span>
        </span>
        </p>
        <p>

        New Password:
        <input type="password" name="pass" ng-model="update.password" placeholder="Password" required/>
        <span style="color:red" ng-show="editform.pass.$dirty && editform.pass.$invalid">
        <span class="error" ng-show="editform.pass.$error.required"> Password is required</span>
        </span>
        </p>

        <button type="submit" class="btn btn-success btn-lg pull-centre" ng-click="update.updateUser()" ng-disabled="editform.$invalid">
           Update
        </button>
    </div>
</form>

