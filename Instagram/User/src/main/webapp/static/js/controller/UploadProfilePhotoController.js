(function () {
    angular.module("userModule").controller("UploadProfilePhotoController", UploadProfilePhotoController);
    UploadProfilePhotoController.$inject = ['HttpService', '$rootScope', '$uibModalInstance', '$localStorage'];

    function UploadProfilePhotoController(HttpService, $rootScope, $uibModalInstance, $localStorage) {
        var vm = this;
        vm.url = "/uploadProfilePhoto";
        vm.imageName = [];
        $rootScope.profile = '';
        vm.submitClicked=false;
        vm.showUploadImage=false;

        vm.uploadPhoto = uploadPhoto;
        vm.close = close;

        function uploadPhoto() {
            vm.submitClicked=true;

            vm.obj = {
                'profile_pic': vm.imageName.base64,
                'username': $localStorage.storedObj.username
            }

            HttpService.post(vm.url, vm.obj).then(
                function (value) {
                    vm.submitClicked=true;
                    $rootScope.saved = true;
                    vm.showUploadImage=true;
                    $uibModalInstance.close('save');
                },
                function (reason) {
                    vm.submitClicked=false;
                    $rootScope.message = "Error occured";
                    $rootScope.saved = true;
                }
            );
        }

        function close() {
            $uibModalInstance.dismiss('close');
        }
    }
})();