(function () {
    angular.module("adminModule").controller("AdminPhotoModalController", AdminPhotoModalController);

    AdminPhotoModalController.$inject = ['HttpService','$rootScope','$uibModalInstance'];

    function AdminPhotoModalController(HttpService,$rootScope,$uibModalInstance) {
        var vm = this;
        vm.clickButton = false;
        vm.admin = $rootScope.clickedAdmin;
        vm.updatePhoto = updatePhoto;
        vm.cancel = cancel;

        function updatePhoto() {
            vm.clickButton = true;
            $rootScope.clickedAdmin.image = vm.imageName.base64;
            HttpService.post("/update", vm.admin).then(
                function (value) {
                    vm.clickButton = true;
                    $rootScope.message = "Updated successfully";
                    vm.saved = true;
                    $uibModalInstance.close('save');
                }, function (reason) {
                    vm.clickButton = false;
                    $rootScope.message = "Error Occurred";
                    vm.saved = false;
                });
        }

        function cancel() {
            $uibModalInstance.dismiss('close');
        }
    }
})();
