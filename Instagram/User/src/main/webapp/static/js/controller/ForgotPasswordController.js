(function () {
    angular.module('userModule').controller('ForgotPasswordController', ForgotPasswordController);
    ForgotPasswordController.$inject = ['$location', 'HttpService'];

    function ForgotPasswordController($location, HttpService) {
        var vm = this;
        vm.username = '';
        vm.error_msg=false;
        vm.submitClicked=false;
        vm.forgotPass=forgotPass;

        function forgotPass() {
            vm.update={
                'username':vm.username
            };
            HttpService.post("/forgotPassword",vm.update).then(function (value) {
                    vm.submitClicked=true;
                    alert("Thanks! You can now login with the password we have sent you in your mail..");
                    $location.path("/login");
                },
                function (reason) {
                    vm.error_msg = true;
                    vm.submitClicked=false;
                })

        }
    }
})()