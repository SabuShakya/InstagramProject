(function () {
    angular.module("userModule").controller("EditProfileController",EditProfileController );
    EditProfileController.$inject=['HttpService','$uibModalInstance', '$rootScope','$localStorage'];
    function EditProfileController(HttpService,$uibModalInstance, $rootScope,$localStorage) {
        var vm= this;
        vm.profilePic = [];
        vm.url="/updateProfile";
        vm.uploadPhoto = uploadPhoto;
        vm.close = close;

        function uploadPhoto() {
            vm.obj = {'profileImg':vm.profilePic.base64
            }

            HttpService.post(vm.url, vm.obj).then(
                function (value) {
                    $rootScope.message = "Picture uploaded successfully";
                    $rootScope.saved = true;
                },
                function(reason){
                    $rootScope.message ="Error occured";
                    $rootScope.saved = true;
                }
            );
            $uibModalInstance.close('save');
        }

        function close(){
            $uibModalInstance.dismiss('close');
        }
    }
})();