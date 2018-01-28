<%--<form name="myForm" class="form-horizontal" novalidate>--%>
    <%--<br>--%>
    <%--<div class="imgcontainer">--%>
        <%--<img src="modules/images/img_avatar2.png" width="100" height="150" alt="Avatar" class="avatar">--%>
    <%--</div>--%>

    <%--<div class="container">--%>
        <%--<label class="control-label col-sm-2" for="userName"><b>UserName:</b></label>--%>
            <%--<input type="text" class="form-control" ng-model="login.userName" id="userName"--%>
                   <%--placeholder="Enter user name" name="userName" required/><br>--%>
            <%--<span style="color:red" ng-show="myForm.userId.$dirty && myForm.userName.$invalid">--%>
        <%--<span ng-show="myForm.userName.$error.required">UserName is required.</span>--%>
      <%--</span>--%>
      <%--<label class="control-label col-sm-2" for="pwd">Password:</label>--%>

          <%--<input type="password" class="form-control" id="pwd" ng-model="login.password"--%>
                       <%--placeholder="Enter password" name="password" required/><br>--%>
          <%--<span style="color:red" ng-show="myForm.pwd.$dirty && myForm.pwd.$invalid">--%>
          <%--<span ng-show="myForm.pwd.$error.required">Password required</span>--%>
      <%--</span>--%>

    <%--<div class="form-group">--%>
        <%--<button type="button" class="btn btn-default" ng-disabled="myForm.$invalid" ng-click="login.loginUser()">Login--%>
        <%--</button>--%>
        <%--<button type="button" class="btn btn-default" ng-click="login.signup()">Signup</button>--%>
    <%--</div>--%>
    <%--<div ng-hide ="login.valid">--%>
        <%--<span style="color:red">{{login.errormsg}}</span>--%>
    <%--</div>--%>

  <%--</div>--%>
<%--</form>--%>

<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info" >
            <div class="panel-heading">
                <div class="panel-title">Login</div>
            </div>
            <div style="padding-top:30px" class="panel-body" >
                <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                <form name="myForm" class="form-horizontal" novalidate>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input type="text" class="form-control" ng-model="login.userName" id="userName"
                               placeholder="Enter user name" name="userName" required/><br>
                        <span style="color:red" ng-show="myForm.userId.$dirty && myForm.userName.$invalid">
                            <span ng-show="myForm.userName.$error.required">UserName is required.</span>
                        </span>
                    </div>

                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input type="password" class="form-control" id="pwd" ng-model="login.password"
                        placeholder="Enter password" name="password" required/><br>
                        <span style="color:red" ng-show="myForm.pwd.$dirty && myForm.pwd.$invalid">
                             <span ng-show="myForm.pwd.$error.required">Password required</span>
                        </span>
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-default" ng-disabled="myForm.$invalid" ng-click="login.loginUser()">Login
                        </button>
                        <button type="button" class="btn btn-default" ng-click="login.signup()">Signup</button>
                    </div>
                    <div ng-hide ="login.valid">
                        <span style="color:red">{{login.errormsg}}</span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

