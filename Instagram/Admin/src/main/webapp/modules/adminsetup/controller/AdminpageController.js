(function () {
    angular.module("adminModule").controller("AdminpageController", AdminpageController);

    AdminpageController.$inject = ['HttpService',
        '$uibModal',
        '$rootScope',
        '$localStorage',
        '$location'];

    function AdminpageController(HttpService, $uibModal, $rootScope, $localStorage, $location) {
        var vm = this;
        vm.adminId = '';
        vm.totalUsers = '';
        vm.activeUsers = '';
        vm.totalUploads = '';
        vm.uploadsPerDay = '';

        vm.getLogs = getLogs;
        vm.getLoggedAdminInfo = getLoggedAdminInfo;
        // vm.openUserLog = openUserLog;
        getLoggedAdminInfo();
        getLogs();

        function getLoggedAdminInfo() {
            HttpService.get("/getAdminId/" + $localStorage.adminObj.tokenNo + "/"
                + $localStorage.adminObj.userName).then(
                function (value) {
                    vm.adminId = $localStorage.adminObj.name;
                    $rootScope.adminId = $localStorage.adminObj.name;
                    console.log(vm.adminId);
                }, function (reason) {
                    vm.showAll = false;
                    // openLoginModal();
                });
        }

        function getLogs() {
            HttpService.get("/getUserLogs").then(function (value) {
                vm.totalUsers = value.totalUsers;
                vm.activeUsers = value.activeUsers;
                vm.totalUploads = value.totalUploads;
                vm.uploadsPerDay = value.uploadsPerDay;
            }, function (reason) {
                console.log("Error:" + reason);
            });
        }
        // function openUserLog() {
        //     vm.modalInstance = $uibModal.open({
        //         ariaLabelledBy: 'modal-title',
        //         ariaDescribedBy: 'modal-body',
        //         templateUrl: 'modules/views/userLog.jsp',
        //         controller: 'UserLogController',
        //         controllerAs: 'userLog',
        //         size: 'lg'
        //     });
        // }

    }
})();