(function () {
    angular.module("adminModule").controller("ManageAdminController", ManageAdminController);
    ManageAdminController.$inject = ['HttpService', '$uibModal', '$rootScope', '$localStorage', 'NgTableParams'];

    function ManageAdminController(HttpService, $uibModal, $rootScope, $localStorage, NgTableParams) {
        var vm = this;
        vm.adminList = [];
        vm.disableEditDeleteButtons = false;
        $rootScope.clickedAdmin = '';
        $rootScope.message = '';
        $rootScope.saved = false;
        vm.url = "/getAllAdmins";

        vm.showAdminList = showAdminList;
        vm.openEditModal = openEditModal;
        vm.openDeleteModal = openDeleteModal;
        vm.openPhotoModal = openPhotoModal;

        showAdminList();

        function showAdminList() {
            HttpService.get(vm.url).then(function (value) {
                vm.adminList = value;
                angular.forEach(vm.adminList, function (admin, k) {
                    if ($localStorage.adminObj.userName == admin.userName) {
                        vm.disableEditDeleteButtons = true;
                        admin.disableEditDeleteButtons = vm.disableEditDeleteButtons;
                    }
                });
                // for ng-Table
                vm.tableParams = new NgTableParams({
                    page: 1,
                    count: 3,
                    sorting: {userName: 'asc'}
                }, {dataset: vm.adminList});

            }, function (reason) {
                console.log("Something occurred" + reason);
            });
        }

        function openEditModal(admin) {
            $rootScope.clickedAdmin = admin;
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'modules/views/editModal.jsp',
                controller: 'EditModalController',
                controllerAs: 'modalController',
                size: 'lg'
            });
            vm.modalInstance.result.then(
                function () {
                    showAdminList();
                },
                function () {
                })
        }

        function openDeleteModal(admin) {
            $rootScope.clickedAdmin = admin;
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'modules/views/confirmDelete.jsp',
                controller: 'EditModalController',
                controllerAs: 'modalController',
                size: 'lg'
            });
            vm.modalInstance.result.then(
                function () {
                    showAdminList();
                },
                function () {
                })
        }

        function openPhotoModal(admin) {
            $rootScope.clickedAdmin = admin;
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'modules/views/adminPhotoModal.jsp',
                controller: 'EditModalController',
                controllerAs: 'modalController',
                size: 'lg'
            });
            vm.modalInstance.result.then(
                function () {
                    showAdminList();
                },
                function () {
                })
        }
    }
})();
