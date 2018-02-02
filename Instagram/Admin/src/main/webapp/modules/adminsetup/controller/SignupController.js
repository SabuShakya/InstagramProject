(function () {
    angular.module("adminModule").controller("SignupController",SignupController);

    SignupController.$inject = ['SignupService','$location'];
    function SignupController(SignupService,$location) {
        var vm = this;
        vm.name = '';
        vm.userName = '';
        vm.email = '';
        vm.password = '';
        vm.repassword = '';
        vm.response = '';
        vm.match = false;
        vm.show_error_msg = false;
        vm.clickButton = false;

        vm.saveAdmin = saveAdmin;
        vm.url = "/signup";
        function saveAdmin() {
            vm.clickButton = true;
            vm.newAdminObj={
                'name':vm.name,
                'userName':vm.userName,
                'email':vm.email,
                'password':vm.password
            };
            SignupService.saveAdmin(vm.url,vm.newAdminObj).then(function (value) {
                vm.clickButton = true;
                $location.path("/login");
            },function (reason) {
                vm.clickButton = false;
                vm.show_error_msg = true;
            });
        }
    }
})();