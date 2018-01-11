(function() {
    angular.module('userModule').controller('ImgUploadController', ImgUploadController);

    ImgUploadController.$inject=['HttpService','$uibModalInstance', '$rootScope'];

    function ImgUploadController (HttpService, $uibModalInstance, $rootScope) {
        var vm= this;
        vm.imageName = [];
        vm.caption = '';
        vm.likes= 0;
        vm.comments ='';

        vm.url="/upload";
        vm.uploadPhoto = uploadPhoto;
        vm.close = close;

        function uploadPhoto() {
            vm.obj = {'image_path':vm.imageName.base64,
                        'id': null,
                        'created_date': new Date(),
                        'caption': vm.caption,
                        // 'likes' : vm.likes,
                        // 'comments': vm.comments
                }

            HttpService.postPhotos(vm.url, vm.obj).then(
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
