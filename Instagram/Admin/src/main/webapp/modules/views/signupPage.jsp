<h2 class="text-center">Sign Up</h2>
<form name="myForm" class="form-horizontal" novalidate>
    <div class="form-group">
        <label class="control-label col-sm-2" for="name">Name:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" ng-model="signup.name" id="name" placeholder="Enter name" name="name" required/><br>
            <span style="color:red" ng-show="myForm.name.$dirty && myForm.name.$invalid">
        <span ng-show="myForm.name.$error.required">Name is required.</span>
        </span>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="userName">UserName:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" ng-model="signup.userName" id="userName" placeholder="Enter name" name="userName" required/><br>
            <span style="color:red" ng-show="myForm.userName.$dirty && myForm.userName.$invalid">
        <span ng-show="myForm.userName.$error.required">userName is required.</span>
        </span>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="email">Email:</label>
        <div class="col-sm-10">
            <input type="email" class="form-control" ng-model="signup.email" id="email" placeholder="Enter email" name="email" required/><br>
            <span style="color:red" ng-show="myForm.email.$dirty && myForm.email.$invalid">
          <span ng-show="myForm.email.$error.required">Email is required.</span>
          <span ng-show="myForm.email.$error.email">Invalid email address.</span>
        </span>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="pwd">Password:</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="pwd" ng-model="signup.password" placeholder="Enter password" name="pwd" required/><br>
            <span style="color:red" ng-show="myForm.pwd.$dirty && myForm.pwd.$invalid">
          <span ng-show="myForm.pwd.$error.required">Password required</span>
        </span>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2"  ng-model="signup.repassword" for="re-pwd">Re-Password:</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="re-pwd" placeholder="Enter password again" name="re-pwd" required/>
            <span style="color:red" ng-show="signup.match">Password donot match</span>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-default" ng-disabled="signup.clickButton" ng-click="signup.saveAdmin()">SignUp</button>
        </div>
    </div>
</form>
<span style="color:red" ng-show="signup.show_error_msg">Error Occurred try again</span>