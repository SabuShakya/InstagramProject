(function () {
    angular.module('userModule').controller('LoginController', LoginController);
    LoginController.$inject=['$location','HttpService'];

    function LoginController($location, HttpService) {
        var vm= this;
        vm.uname = '';
        vm.email = '';
        vm.password = '';
        vm.error_msg = '';
        vm.url = "/login";
        vm.valid= true;

        vm.loginUser = loginUser;

        function loginUser() {
            vm.user = {
                uname: vm.uname,
                email: vm.email,
                password: vm.password
            };
            HttpService.postLogin(vm.url, vm.user).then(
                function(value){
                    console.log("success");
                    $location.path("/main");
                },
                function(error){
                    vm.valid=false;
                    vm.error_msg = "Incorrect username or password";
                    console.log("error occurred");
                }
            );
        }
    }
})();