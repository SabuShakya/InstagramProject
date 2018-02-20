<h2> Reset Password </h2><br>
<p>
    We can help you reset your password using your username linked to your account.</p>

<form name="editform" novalidate>
    <div class="container">

        <p>
            Username:
            <input type="text" name="username" ng-model="forgotPass.username" placeholder="Instagram username" required/>
            <span style="color:red" ng-show="editform.email.$dirty && editform.email.$invalid">
            <span class="error" ng-show="editform.email.$error.required"> Username is required</span>
            <span class="error" ng-show="editform.email.$error.email">Enter a valid username</span>
            </span>
        </p>

        <%--set email to unique and use email to reset password--%>

        <%--<p>--%>
            <%--Email:--%>
            <%--<input type="text" name="email" ng-model="forgotPass.email" placeholder="Email ID" required/>--%>
            <%--<span style="color:red" ng-show="editform.email.$dirty && editform.email.$invalid">--%>
            <%--<span class="error" ng-show="editform.email.$error.required"> Email is required</span>--%>
            <%--<span class="error" ng-show="editform.email.$error.email">Enter a valid email</span>--%>
            <%--</span>--%>
        <%--</p>--%>

        <button type="submit" class="btn btn-success pull-centre" ng-click="forgotPass.forgotPass()" ng-disabled="editform.$invalid" ng-disabled="forgotPass.submitClicked">
            Reset Password
        </button>

        <br> <br>
        <%--<span ng-show="update.successMsg">Password changed successfully!!</span>--%>
        <span style="color:red" ng-show="forgotPass.error_msg">Enter correct username</span>
    </div>
</form>