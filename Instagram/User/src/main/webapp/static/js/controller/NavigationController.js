(function () {
    angular.module("userModule").controller("NavigationController",NavigationController);

    NavigationController.$inject = ['HttpService','$localStorage','$location'];

    function NavigationController(HttpService,$localStorage,$location) {
        var vm = this;
        vm.userName = $localStorage.storedObj.username;
        vm.user = {};
        vm.logout =logout;

        function logout() {
            vm.user = $localStorage.storedObj;
            $localStorage.storedObj = null;
            HttpService.post("/logout", vm.user).then(
                function (value) {
                    // $localStorage.storedObj = {};
                    $location.path("/login");
                },
                function (reason) {
                    console.log(reason);
                }
            )
        };
    }
})();