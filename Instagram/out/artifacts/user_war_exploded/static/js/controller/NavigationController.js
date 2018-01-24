(function () {
    angular.module("userModule").controller("NavigationController",NavigationController);

    NavigationController.$inject = ['HttpService','$localStorage','$location'];

    function NavigationController(HttpService,$localStorage,$location) {
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