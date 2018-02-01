(function () {
    angular.module("adminModule").controller("ActiveUsersListController", ActiveUsersListController);

    ActiveUsersListController.$inject = ['HttpService', '$log', '$localStorage'];

    function ActiveUsersListController(HttpService, $log, $localStorage) {
        var vm = this;
        vm.listOfUsers = '';
        vm.title = "Total Active Users";
        //for Pagination
        vm.totalItems = '';
        vm.currentPage = 1;
        vm.maxSize = 3;

        vm.getTotalActiveUsers = getTotalActiveUsers;
        vm.openProfile = openProfile;
        vm.pageChanged = pageChanged;

        getTotalActiveUsers();

        function getTotalActiveUsers() {
            vm.url = "/getTotalActiveUsers" + "?page=" + vm.currentPage + "&size=" + vm.maxSize;
            HttpService.get(vm.url).then(
                function (value) {
                    vm.listOfUsers = value;
                    vm.totalItems = value[0].totalItems;
                }, function (reason) {
                    console.log("error" + reason);
                });
        }

        function pageChanged() {
            $log.log("Page changed to:" + vm.currentPage);
            getTotalActiveUsers();
        };

        function openProfile(user) {
            $localStorage.openProfileOf = user;
        }
    }
})();
