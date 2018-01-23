(function () {
    angular.module("userModule").controller("LogOutController",LogOutController);

    LogOutController.$inject = ['HttpService','$localStorage','$location'];

    function LogOutController(HttpService,$localStorage,$location) {
        var vm = this;
        vm.userName = $localStorage.storedObj.username;
        vm.logout =logout;

        function logout() {
            HttpService.post("/logout", $localStorage.storedObj).then(
                function (value) {
                    $localStorage.storedObj = {};
                    $location.path("/login");
                },
                function (reason) {
                    console.log(reason);
                }
            )
        };
    }
})();