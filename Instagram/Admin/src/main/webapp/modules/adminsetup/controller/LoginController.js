(function () {
    angular.module("adminModule").controller("LoginController", LoginController);

    LoginController.$inject = ['$location', 'LoginService', 'HttpService', '$localStorage'];

    function LoginController($location, LoginService, HttpService, $localStorage) {
        var vm = this;
        vm.userName = '';
        vm.password = '';
        vm.errormsg = '';
        vm.valid = true;
        vm.booleanValue = true;
        vm.url = "/login";

        vm.loginUser = loginUser;
        vm.signup = signup;

        if (!($localStorage.adminObj == null)) {
            $location.path("/adminPage");
        }

        function loginUser() {
            vm.adminObj = {
                'userName': vm.userName,
                'password': vm.password
            };
            LoginService.verifyAdmin(vm.url, vm.adminObj)
                .then(function (response) {
                    $localStorage.adminObj = {
                        name: response.name,
                        tokenNo: response.tokenNo,
                        userName: response.userName,
                        status: response.status
                    };
                    $location.path("/adminPage");
                }, function (reason) {
                    vm.valid = false;
                    vm.errormsg = reason.errorMsg;
                    console.log(reason.developerMessage);
                });
        }

        function signup() {
            $location.path("/signup");
        };

    }
})();