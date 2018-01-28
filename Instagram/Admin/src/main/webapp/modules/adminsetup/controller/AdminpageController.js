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

        vm.openUserLog = openUserLog;
        vm.openLoginModal = openLoginModal;

        HttpService.get("/getAdminId/" + $localStorage.adminObj.tokenNo + "/"
            + $localStorage.adminObj.userName).then(
            function (value) {
                vm.adminId = $localStorage.adminObj.name;
                $rootScope.adminId=$localStorage.adminObj.name;
                console.log(vm.adminId);
            }, function (reason) {
                vm.showAll = false;
                openLoginModal();
            });

        function openUserLog() {
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'modules/views/userLog.jsp',
                controller: 'UserLogController',
                controllerAs: 'userLog',
                size: 'lg'
            });
        }

        function openLoginModal() {
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'modules/views/sessionLostModal.jsp',
                controller: 'EditModalController',
                controllerAs: 'modalController',
                size: 'lg'
            });
        }
    }
})();