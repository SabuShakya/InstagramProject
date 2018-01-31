(function () {
    angular.module("adminModule").controller("EditModalController", EditModalController);

    EditModalController.$inject = ['HttpService', '$uibModalInstance', '$rootScope', '$location'];

    function EditModalController(HttpService, $uibModalInstance, $rootScope, $location) {
        var vm = this;
        vm.admin = $rootScope.clickedAdmin;
        vm.url = "/update";
        vm.imageName = $rootScope.clickedAdmin.image;
        vm.cancel = cancel;
        vm.update = update;
        vm.deleteAdmin = deleteAdmin;
        vm.updatePhoto = updatePhoto;
        vm.clickButton = false;
        vm.ok = ok;

        function update() {
            vm.clickButton = true;
            $rootScope.clickedAdmin.image = null;
            HttpService.post("/update", vm.admin).then(
                function (value) {
                    vm.clickButton = true;
                    $rootScope.message = "Updated successfully";
                    $rootScope.saved = true;
                    // $rootScope.adminId = vm.admin.name;
                    $uibModalInstance.close('save');
                }, function (reason) {
                    vm.clickButton = false;
                    $rootScope.message = "Error Occurred";
                    $rootScope.saved = true;
                });
        }

        function updatePhoto() {
            vm.clickButton = true;
            $rootScope.clickedAdmin.image = vm.imageName.base64;
            HttpService.post("/update", vm.admin).then(
                function (value) {
                    vm.clickButton = true;
                    $rootScope.message = "Updated successfully";
                    $rootScope.saved = true;
                    $uibModalInstance.close('save');
                }, function (reason) {
                    vm.clickButton = false;
                    $rootScope.message = "Error Occurred";
                    $rootScope.saved = true;
                });
        }

        function deleteAdmin() {
            HttpService.post("/deleteAdmin", vm.admin).then(function (value) {
                $rootScope.message = "Deleted successfully";
                $rootScope.saved = true;
                $uibModalInstance.close('save');
            }, function (reason) {
                $rootScope.message = "Error Occurred";
                $rootScope.saved = true;
            });
        }

        function cancel() {
            $uibModalInstance.dismiss('close');
        }

        function ok() {
            $location.path("/login");
            $uibModalInstance.dismiss();
        }
    }
})();