(function () {
    angular.module("adminModule").controller("LoginController", LoginController);

    LoginController.$inject = ['$location', 'LoginService','HttpService','$localStorage'];

    function LoginController($location,LoginService,HttpService,$localStorage) {
        var vm = this;
        vm.userName = '';
        vm.password = '';
        vm.errormsg = '';
        vm.valid = true;
        vm.booleanValue = true;
        vm.url = "/login";

        vm.loginUser = loginUser;
        vm.signup = signup;

        function loginUser() {
            vm.adminObj = {
                'userName' : vm.userName,
                'password' : vm.password
            };
        LoginService.verifyAdmin(vm.url,vm.adminObj)
            .then(function (response) {
                $localStorage.tokenNo = response.tokenNo;
                $localStorage.userName = response.userName;
                $location.path("/adminPage");
        },function (reason) {
                    vm.valid = false;
                    vm.errormsg = "User not registered";
            });
        }

        function signup() {
            $location.path("/signup");
        };

    }
})();