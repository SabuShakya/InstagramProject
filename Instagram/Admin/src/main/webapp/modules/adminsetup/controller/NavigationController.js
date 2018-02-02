(function () {
    angular.module("adminModule").controller("NavigationController", NavigationController);

    NavigationController.$inject = ['HttpService', '$localStorage', '$location'];

    function NavigationController(HttpService, $localStorage, $location) {
        var vm = this;
        vm.admin = {};
        vm.logout = logout;

        function logout() {
            vm.admin = $localStorage.adminObj;
            $localStorage.adminObj = null;
            HttpService.post("/logout", vm.admin).then(
                function (value) {
                    // $localStorage.adminObj = {};
                    $location.path("/login");
                },
                function (reason) {
                    console.log(reason);
                })
        };
    }
})();