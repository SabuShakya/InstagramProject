(function() {
    angular.module("userModule").controller("UpdateController", UpdateController);

    UpdateController.$inject = ['HttpService', '$rootScope', '$location'];

    function UpdateController(HttpService, $rootScope, $location) {
        var vm = this;
        vm.username='';
        vm.password='';
        vm.url = "/update";

        vm.updateUser = updateUser;

        function updateUser() {
            vm.obj={
                'username':vm.username,
                'password':vm.password
            };

            HttpService.post(vm.url, vm.obj).then(
                function (value) {
                    alert("Your password has been changed successfully.. Login Again!!");
                    $location.path("/login")
                    console.log("success");
                }, function (reason) {
                    console.log("eror");
                });
        }
    }
})();