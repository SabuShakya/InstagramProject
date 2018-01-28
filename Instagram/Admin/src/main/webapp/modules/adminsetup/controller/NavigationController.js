(function () {
    angular.module("adminModule").controller("NavigationController",NavigationController);

    NavigationController.$inject = ['HttpService','$localStorage','$location'];

    function NavigationController(HttpService,$localStorage,$location) {
        var vm = this;
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