(function () {
    angular.module("adminModule").controller("EditModalController", EditModalController);

    EditModalController.$inject = ['HttpService', '$uibModalInstance', '$rootScope', '$location','$uibModal'];

    function EditModalController(HttpService, $uibModalInstance, $rootScope, $location,$uibModal) {
        var vm = this;
        vm.admin = $rootScope.clickedAdmin;
        vm.url = "/update";
        vm.imageName = $rootScope.clickedAdmin.image;
        vm.clickButton = false;
        vm.message = '';
        
        vm.cancel = cancel;
        vm.update = update;
        vm.deleteAdmin = deleteAdmin;
        vm.openPhotoUploadModal =openPhotoUploadModal;
        vm.getAdminPhoto = getAdminPhoto;
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

        // function updatePhoto() {
        //     vm.clickButton = true;
        //     $rootScope.clickedAdmin.image = vm.imageName.base64;
        //     HttpService.post("/update", vm.admin).then(
        //         function (value) {
        //             vm.clickButton = true;
        //             $rootScope.message = "Updated successfully";
        //             $rootScope.saved = true;
        //             $uibModalInstance.close('save');
        //         }, function (reason) {
        //             vm.clickButton = false;
        //             $rootScope.message = "Error Occurred";
        //             $rootScope.saved = true;
        //         });
        // }

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
        
        function openPhotoUploadModal() {
            vm.modalInstance = $uibModal.open({
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: 'modules/views/adminPhotoModal.jsp',
                controller: 'AdminPhotoModalController',
                controllerAs: 'modalController',
                size: 'md'
            });
            vm.modalInstance.result.then(
                function(){
                    getAdminPhoto();
                },
                function(){});
        }

        function getAdminPhoto() {
            HttpService.get("/getAdminPhoto/"+$rootScope.clickedAdmin.userName).then(function (value) {
                $rootScope.clickedAdmin.image = value.image;
            },function (reason) {
                vm.message = "Error Occurred";
                console.log(reason);
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