(function () {
    angular.module("adminModule").controller("UsersListController", UsersListController);

    UsersListController.$inject = ['HttpService', '$log', '$localStorage'];

    function UsersListController(HttpService, $log, $localStorage) {
        var vm = this;
        vm.listOfUsers = '';
        vm.title = "Total Users";
        //for Pagination
        vm.totalItems = '';
        vm.currentPage = 1;
        vm.maxSize = 3;

        vm.getTotalUsers = getTotalUsers;
        vm.getTotalActiveUsers = getTotalActiveUsers();
        vm.openProfile = openProfile;
        vm.pageChanged = pageChanged;

        getTotalUsers();

        function getTotalUsers() {
            vm.url = "/getTotalUsers" + "?page=" + vm.currentPage + "&size=" + vm.maxSize;
            HttpService.get(vm.url).then(
                function (value) {
                    vm.listOfUsers = value;
                    vm.totalItems = value[0].totalItems;
                }, function (reason) {
                    console.log("error" + reason);
                });
        }

        function getTotalActiveUsers() {
            vm.url = "/getTotalActiveUsers" + "?page=" + vm.currentPage + "&size=" + vm.maxSize;
            HttpService.get(vm.url).then(
                function (value) {
                    vm.listOfUsers = vm.listOfUsers.concat(value);
                    vm.totalItems = value[0].totalItems;
                }, function (reason) {
                    console.log("error" + reason);
                });
        }

        function pageChanged() {
            $log.log("Page changed to:" + vm.currentPage);
            getTotalUsers();
        };

        function openProfile(user) {
            $localStorage.openProfileOf = user;
        }
    }
})();
