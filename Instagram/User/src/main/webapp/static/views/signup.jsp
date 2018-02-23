<h2 class="text-center">Sign Up</h2>
<form name="signupform" novalidate>
    <div class="container">
        <p>
            Full Name:
            <input type="text" name="fullName" ng-model="signup.fullName" placeholder="Full Name" required/>
            <span style="color:red" ng-show="signupform.fullName.$dirty && signupform.fullName.$invalid">
            <span class="error" ng-show="signupform.fullName.$error.required"> Fullname is required</span>
            </span>
        </p>

        <p>
            Username:
            <input type="text" name="uname" ng-model="signup.username" placeholder="Username" required/>
        <span style="color:red" ng-show="signupform.uname.$dirty && signupform.uname.$invalid">
            <span class="error" ng-show="signupform.uname.$error.required"> Username is required</span>
        </span>
        </p>

        <p>
            Email:
            <input type="text" name="email" ng-model="signup.email" placeholder="Email ID" required/>
            <span style="color:red" ng-show="signupform.email.$dirty && signupform.email.$invalid">
            <span class="error" ng-show="signupform.email.$error.required"> Email is required</span>
            <span class="error" ng-show="signupform.email.$error.email">Enter a valid email</span>
            </span>
        </p>
    </div>

    <button type="submit" class="btn btn-success btn-lg pull-centre" ng-disabled="signup.submitClicked" ng-disabled="signupform.$invalid" ng-click="signup.createUser()" >
        Submit form
    </button>

    <div ng-show="signup.loading">
        <i class="fa fa-spinner fa-spin" style="font-size:50px"></i>
    <%--<div class="loader">--%>
    <%--</div>--%>
    </div>

</form>
<span style="git color:red" ng-show="signup.error_msg">Error Occurred try again</span>

<style>
    input[type=text], select, textarea {
        width: 100%;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        margin-top: 6px;
        margin-bottom: 16px;
        resize: vertical;
    }
    input[type=email], input[type="password"], input[type="repassword"]{
        box-sizing: border-box;
        width: 100%;
        padding: 12px;
    }
    .loader {
        border: 16px solid #f3f3f3;
        border-radius: 50%;
        border-top: 16px solid #3498db;
        width: 120px;
        height: 120px;
        -webkit-animation: spin 2s linear infinite; /* Safari */
        animation: spin 2s linear infinite;
    }

    /* Safari */
    @-webkit-keyframes spin {
        0% { -webkit-transform: rotate(0deg); }
        100% { -webkit-transform: rotate(360deg); }
    }

    @keyframes spin {
        0% { transform: rotate(0deg); }
        100% { transform: rotate(360deg); }
    }
</style>
