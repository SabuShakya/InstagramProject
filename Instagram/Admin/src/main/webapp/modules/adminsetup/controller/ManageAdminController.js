(function () {
    angular.module("adminModule").controller("ManageAdminController", ManageAdminController);
    ManageAdminController.$inject = ['HttpService', '$uibModal', '$rootScope', '$localStorage'];

    function ManageAdminController(HttpService, $uibModal, $rootScope, $localStorage) {
        var vm =this;
        vm.adminList = [];
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
            }, function (reason) {
                console.log("Something occurred" + reason);
            });
        }

        function openEditModal(admin) {
            $rootScope.clickedAdmin = admin;
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/modules/views/editModal.jsp',
                controller: 'EditModalController',
                controllerAs: 'modalController',
                size: 'lg'
            });
            vm.modalInstance.result.then(
                function(){
                    showAdminList();
                },
                function(){})
        }

        function openDeleteModal(admin) {
            $rootScope.clickedAdmin = admin;
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/modules/views/confirmDelete.jsp',
                controller: 'EditModalController',
                controllerAs: 'modalController',
                size: 'lg'
            });
            vm.modalInstance.result.then(
                function(){
                    showAdminList();
                },
                function(){})
        }

        function openPhotoModal(admin) {
            $rootScope.clickedAdmin = admin;
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/modules/views/adminPhotoModal.jsp',
                controller: 'EditModalController',
                controllerAs: 'modalController',
                size: 'lg'
            });
            vm.modalInstance.result.then(
                function(){
                    showAdminList();
                },
                function(){})
        }
    }
})()
