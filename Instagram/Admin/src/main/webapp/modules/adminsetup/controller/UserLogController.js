(function () {
    angular.module("adminModule").controller("UserLogController",UserLogController);

    UserLogController.$inject = ['HttpService','$uibModalInstance'];

    function UserLogController(HttpService,$uibModalInstance) {
        var vm = this;
        vm.totalUsers = '';
        vm.activeUsers = '';
        vm.totalUploads = '';
        vm.uploadsPerDay = '';
        vm.getLogs = getLogs;
        vm.close =close;
        getLogs();

        function getLogs() {
            HttpService.get("/getUserLogs").then(function (value) {
                vm.totalUsers = value.totalUsers;
                vm.activeUsers = value.activeUsers;
                vm.totalUploads = value.totalUploads;
                vm.uploadsPerDay = value.uploadsPerDay;
            },function (reason) {
                console.log("Error:"+reason);
            });
        }

        function close() {
            $uibModalInstance.dismiss("close");
        }
    }
})();