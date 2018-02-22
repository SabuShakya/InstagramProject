(function() {
    angular.module('userModule').controller('DeletePhotoMessageController', DeletePhotoMessageController);

    DeletePhotoMessageController.$inject = ['HttpService', '$uibModalInstance', '$rootScope', '$location','$localStorage'];

    function DeletePhotoMessageController(HttpService, $uibModalInstance, $rootScope, $location,$localStorage) {
        var vm = this;
        vm.photoList=[];
        vm.showList = true;
        $rootScope.clickedPhoto='';
        vm.yes=yes;
        vm.no=no;

        function yes(imageName) {
            $rootScope.clickedPhoto={
                image_path:imageName
            };
            HttpService.post("/deletePhoto",$rootScope.clickedPhoto).then(function (value) {
                    console.log("success");
                    $uibModalInstance.close('save');
                    // allPhotos();
                },
                function (reason) {
                    console.log("error"+reason);
                }
            )
        }

        function no(){
            $uibModalInstance.dismiss('close');
        }

    }
})()