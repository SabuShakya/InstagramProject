<link rel="stylesheet" type="text/css" href="static/css/login.css">

<div class="loginbox">
    <img src="static/images/login.png" class="image">
    <h1> Instagram </h1>

    <form name="myForm" class="form-login" novalidate>
        <p> Username</p>
        <input type="text" class="form-control" ng-model="login.username" id="username" placeholder="Enter username" name="username" required/><br>
        <span style="color:red" ng-show="myForm.username.$dirty && myForm.username.$invalid">
            <span ng-show="myForm.username.$error.required">Username is required.</span>
        </span>

        <p> Password</p>
        <input type="password" class="form-control" id="pwd" ng-model="login.password" placeholder="Enter password" name="password" required/><br>
        <span style="color:red" ng-show="myForm.pwd.$dirty && myForm.pwd.$invalid">
            <span ng-show="myForm.pwd.$error.required">Password required</span>
        </span>

        <input type="submit" value="Login" ng-disabled="myForm.$invalid" ng-click="login.loginUser()"/>
        <div ng-hide ="login.valid">
                <span style="color:red">{{login.errormsg}}</span>
        </div>

        <a href="/#!/signup">Signup </a> <br>
        <a href="/#!/forgotPassword">Forgot password</a>

        <div ng-show="login.loading">
            <i class="fa fa-spinner fa-spin" style="font-size:50px"></i>
        </div>
    </form>
</div>
</body>
