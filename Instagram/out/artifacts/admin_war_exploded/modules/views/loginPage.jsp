
<form name="myForm" class="form-horizontal" novalidate>

    <div class="imgcontainer">
        <img src="modules/images/img_avatar2.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">
        <label class="control-label col-sm-2" for="userName"><b>UserName:</b></label>
            <input type="text" class="form-control" ng-model="login.userName" id="userName"
                   placeholder="Enter user name" name="userName" required/><br>
            <span style="color:red" ng-show="myForm.userId.$dirty && myForm.userName.$invalid">
        <span ng-show="myForm.userName.$error.required">UserName is required.</span>
      </span>
      <label class="control-label col-sm-2" for="pwd">Password:</label>

          <input type="password" class="form-control" id="pwd" ng-model="login.password"
                       placeholder="Enter password" name="password" required/><br>
          <span style="color:red" ng-show="myForm.pwd.$dirty && myForm.pwd.$invalid">
          <span ng-show="myForm.pwd.$error.required">Password required</span>
      </span>

    <div class="form-group">
        <button type="button" class="btn btn-default" ng-disabled="myForm.$invalid" ng-click="login.loginUser();">Login
        </button>
        <button type="button" class="btn btn-default" ng-click="login.signup();">Signup</button>
    </div>
    <div ng-hide ="login.valid">
        <span style="color:red">{{login.errormsg}}</span>
    </div>
        <input type="checkbox" checked="checked"> Remember me


    <div class="container" style="background-color:#f1f1f1">
        <button type="button" class="cancelbtn">Cancel</button>
        <span class="psw">Forgot <a href="#">password?</a></span>
    </div>
  </div>
</form>