(function () {
    angular.module('userModule').controller('LoginController', LoginController);
    LoginController.$inject=['$location','HttpService','$localStorage'];

    function LoginController($location, HttpService, $localStorage) {
        var vm= this;
        vm.username = '';
        vm.password = '';
        vm.errormsg = '';
        vm.valid= true;
        vm.booleanValue = true;

        vm.url = "/login";
        vm.loginUser = loginUser;

        function loginUser() {
            vm.user = {
                'username': vm.username,
                'password': vm.password
            };

            HttpService.post(vm.url, vm.user)
                .then(function(response){
                    $localStorage.storedObj={
                        username:response.username,
                        tokenNo :response.tokenNo
                    };
                    $location.path("/profile");
                },
                function(error){
                    vm.valid=false;
                    vm.errormsg = "Incorrect username or password";
                }
            );
        }
    }
})();