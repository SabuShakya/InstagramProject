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

        vm.saveAdmin = saveAdmin;
        vm.url = "/signup";
        function saveAdmin() {
            vm.newAdminObj={
                'name':vm.name,
                'userName':vm.userName,
                'email':vm.email,
                'password':vm.password
            };
            SignupService.saveAdmin(vm.url,vm.newAdminObj).then(function (value) {
                $location.path("/login");
            },function (reason) {
                vm.show_error_msg = true;
            });
        }
    }
})();