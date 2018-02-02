<div ng-include src="'static/views/navigation.html'"></div>
<h2 class="text-center">Update Password Here {{update.userDisplayName}} !!</h2>
<br>

<form name="editform" novalidate>
    <div class="container">
        <p>
        Current:
        <input type="password" name="pass" ng-model="update.oldPassword" placeholder="Current password" required/>
        <span style="color:red" ng-show="editform.pass.$dirty && editform.pass.$invalid">
        <span class="error" ng-show="editform.pass.$error.required"> Old Password is required</span>
        </span>
        </p>

        <p>
            New:
            <input type="password" name="pass" ng-model="update.password" placeholder="New password" required/>
            <span style="color:red" ng-show="editform.pass.$dirty && editform.pass.$invalid">
        <span class="error" ng-show="editform.pass.$error.required"> Password is required</span>
        </span>
        </p>

        <p>
            Verify:
            <input type="password" name="pass" ng-model="update.rePassword" placeholder="Enter password again" required/>
            <span style="color:red" ng-show="update.match"> Password do not match!!</span>
        </span>
        </p>

        <button type="submit" class="btn btn-success btn-lg pull-centre" ng-click="update.updateUser()" ng-disabled="editform.$invalid">
           Update
        </button><br>
        <span ng-show="update.successMsg">Password changed successfully!!</span>
        <span style="color:red" ng-show="update.error_msg">Error Occurred try again</span>
    </div>
</form>

<%--<div align="center">--%>
        <%--<button type="button" class="btn-default" ng-click="update.privateUser()">Private account</button>--%>
        <%--<button type="button" class="btn-default" ng-click="update.publicUser()">Public account</button>--%>
<%--</div>--%>

<div>
    Private account:
    <i class="fa fa-toggle-on active" ng-if="update.status == true" ng-click="update.init()" ng-model="update.status"></i>
    <i class="fa fa-toggle-on fa-rotate-180 inactive" ng-if="update.status == false" ng-click="update.changeStatus()" ng-model="update.status"></i>
    <%--<pre>{{update.status }}</pre>--%>
</div>
