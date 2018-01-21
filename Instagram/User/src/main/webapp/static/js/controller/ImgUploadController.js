(function() {
    angular.module('userModule').controller('ImgUploadController', ImgUploadController);

    ImgUploadController.$inject=['HttpService','$uibModalInstance', '$rootScope','$localStorage'];

    function ImgUploadController (HttpService, $uibModalInstance, $rootScope,$localStorage) {
        var vm= this;
        vm.imageName = [];
        vm.imageList =[];
        vm.listOfImages = [];
        vm.i = 0;
        vm.caption = '';
        vm.url="/upload";
        vm.uploadPhoto = uploadPhoto;
        vm.close = close;


        function uploadPhoto() {
            // for(vm.i =0;vm.i<vm.listOfImages.length;vm.i++){
                angular.forEach(vm.listOfImages, function(listOfImages, key) {
                    vm.imageList[vm.i]=listOfImages.base64;
                    vm.i++;
                });
                // vm.imageList[vm.i] = vm.listOfImages.base64;
            // }
            vm.obj = {
                'imageList': vm.imageList,
                'image_path': '',
                'created_date': new Date(),
                'username':$localStorage.storedObj.username,
                'caption': vm.caption
            };
            HttpService.post(vm.url, vm.obj).then(
                function (value) {
                    $rootScope.message = "Picture uploaded successfully";
                    $rootScope.saved = true;
                },
                function(reason){
                    $rootScope.message ="Error occured";
                    $rootScope.saved = true;
                });
            $uibModalInstance.close('save');
        }

        function close(){
            $uibModalInstance.dismiss('close');
        }
    }
})();
