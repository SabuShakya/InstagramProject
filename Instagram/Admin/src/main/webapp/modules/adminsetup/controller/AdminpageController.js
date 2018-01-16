(function () {
    angular.module("adminModule").controller("AdminpageController", AdminpageController);

    AdminpageController.$inject = ['HttpService',
        '$uibModal',
        '$rootScope',
        '$localStorage',
        '$location'];

    function AdminpageController(HttpService, $uibModal, $rootScope, $localStorage, $location) {
        var vm = this;
        vm.adminList = [];
        vm.adminId = '';
        vm.showList = true;
        vm.showAll = true;
        vm.url = "/getAllAdmins";
        $rootScope.clickedAdmin = '';
        $rootScope.message = '';
        $rootScope.saved = false;

        vm.showAdminList = showAdminList;
        vm.openEditModal = openEditModal;
        vm.openDeleteModal = openDeleteModal;
        vm.openUserLog = openUserLog;
        vm.logout = logout;
        vm.openLoginModal = openLoginModal;
        vm.refreshList = refreshList;

        HttpService.get("/getAdminId/" + $localStorage.adminObj.tokenNo + "/"
            + $localStorage.adminObj.userName).then(
            function (value) {
                vm.adminId = $localStorage.adminObj.name;
                console.log(vm.adminId);
            }, function (reason) {
                vm.showAll = false;
                openLoginModal();
            });

        function showAdminList() {
            HttpService.get(vm.url).then(function (value) {
                vm.adminList = value;
                vm.showList = false;
            }, function (reason) {
                console.log("Something occurred" + reason);
            });
        }

        function refreshList() {
            showAdminList();
            $rootScope.saved = false;
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
        }

        function openUserLog() {
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/modules/views/userLog.jsp',
                controller: 'EditModalController',
                controllerAs: 'modalController',
                size: 'lg'
            });
        }

        function logout() {
            HttpService.post("/logout", $localStorage.adminObj).then(function (value) {
                $localStorage.adminObj = {};
                $location.path("/login");
            }, function (reason) {
                alert("Error Occurred");
            });
        }

        function openLoginModal() {
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: '/modules/views/sessionLostModal.jsp',
                controller: 'EditModalController',
                controllerAs: 'modalController',
                size: 'lg'
            });
        }
    }
})();