(function () {
    angular.module('userModule').controller('LoginController', LoginController);
    LoginController.$inject=['$location','HttpService','$localStorage'];

    function LoginController($location, HttpService, $localStorage) {
        var vm= this;
        vm.username = '';
        vm.email = '';
        vm.password = '';
        vm.error_msg = '';
        vm.url = "/login";
        vm.valid= true;

        vm.loginUser = loginUser;

        function loginUser() {
            vm.user = {
                username: vm.username,
                email: vm.email,
                password: vm.password
            };
            HttpService.postLogin(vm.url, vm.user).then(
                function(value){
                    console.log("success");
                    // $localStorage.user_id = value.user_id;
                    // $localStorage.tokenNo = value.tokenNo;
                    $location.path("/profile");
                },
                function(error){
                    vm.valid=false;
                    vm.error_msg = true;
                    console.log("error occurred");
                }
            );
        }
    }
})();