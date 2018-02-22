(function() {
    angular.module('userModule').controller('ImgUploadController', ImgUploadController);

    ImgUploadController.$inject=['HttpService','$uibModalInstance', '$rootScope','$localStorage'];

    function ImgUploadController (HttpService, $uibModalInstance, $rootScope,$localStorage) {
        var vm= this;
        vm.imageName = [];
        vm.imageList =[];
        vm.listOfImages = {imageName:'',
                            caption:''};
        vm.i = 0;
        vm.submitClicked=false;
        vm.showCaptionOptions=false;
        vm.url="/upload";
        vm.uploadPhoto = uploadPhoto;
        vm.close = close;

        function uploadPhoto() {
            vm.submitClicked=false;
            vm.showCaptionOptions=true;
            angular.forEach(vm.listOfImages, function(listOfImages, key) {
                    vm.imageList[vm.i]={
                        imageName:listOfImages.base64,
                        caption:listOfImages.listOfImages.caption};
                    vm.i++;
                });

            vm.obj = {
                'imageList': vm.imageList,
                'image_path': '',
                'created_date': new Date(),
                'username':$localStorage.storedObj.username,
                'caption': vm.imageList.caption
            };
            HttpService.post(vm.url, vm.obj).then(
                function (value) {
                    vm.submitClicked=true;
                    $rootScope.saved = true;
                    $uibModalInstance.close('save');
                },
                function(reason){
                    vm.submitClicked=false;
                    $rootScope.message ="Error occured";
                    $rootScope.saved = true;
                });
        }

        function close(){
            $uibModalInstance.dismiss('close');
        }
    }
})();
