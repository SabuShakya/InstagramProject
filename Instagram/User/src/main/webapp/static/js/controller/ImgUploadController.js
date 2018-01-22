(function() {
    angular.module('userModule').controller('ImgUploadController', ImgUploadController);

    ImgUploadController.$inject=['HttpService','$uibModalInstance', '$rootScope','$localStorage'];

    function ImgUploadController (HttpService, $uibModalInstance, $rootScope,$localStorage) {
        var vm= this;
        vm.imageName = [];
        vm.caption = '';
        vm.url="/upload";
        vm.uploadPhoto = uploadPhoto;
        vm.close = close;

        function uploadPhoto() {
            vm.obj = {'image_path':vm.imageName.base64,
                        'created_date': new Date(),
                        'username':$localStorage.storedObj.username,
                        'caption': vm.caption
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
