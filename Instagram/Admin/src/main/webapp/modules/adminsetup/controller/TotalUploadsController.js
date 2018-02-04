(function () {
    angular.module("adminModule").controller("TotalUploadsController", TotalUploadsController);
    TotalUploadsController.$inject = [
        'HttpService',
        'NgTableParams',
        '$localStorage'];

    function TotalUploadsController(HttpService, NgTableParams,$localStorage) {
        var vm = this;
        vm.title = "Uploads per User";
        vm.getUploadsPerUser = getUploadsPerUser;
        vm.showUploadsOfUser = showUploadsOfUser;
        getUploadsPerUser();

        function getUploadsPerUser() {
            HttpService.get("/usersUploads").then(
                function (value) {
                    vm.tableParams = new NgTableParams({
                        page: 1,
                        count: 5,
                        sorting: {userName: 'asc'}
                    }, {dataset: value})
                }, function (reason) {
                    console.log("Error: " + reason);
                });
        }

        function showUploadsOfUser(user) {
            $localStorage.showUploadsOfUser = user.userName;
        }
    }
})();