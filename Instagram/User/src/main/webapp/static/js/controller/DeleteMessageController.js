(function() {
    angular.module('userModule').controller('DeleteMessageController', DeleteMessageController);

    DeleteMessageController.$inject = ['HttpService', '$uibModalInstance', '$rootScope', '$location','$localStorage'];

    function DeleteMessageController(HttpService, $uibModalInstance, $rootScope, $location,$localStorage) {
        var vm = this;
        vm.photoList=[];
        vm.showList = true;
        $rootScope.clickedPhoto='';
        vm.yes=yes;
        vm.no=no;
        vm.allPhotos=allPhotos;

        function yes(imageName) {
            $rootScope.clickedPhoto={
                image_path:imageName
            };
            HttpService.post("/deletePhoto",$rootScope.clickedPhoto).then(function (value) {
                    console.log("success");
                    $uibModalInstance.close('save');
                },
                function (reason) {
                    console.log("error"+reason);
                }
            )
        }

        function allPhotos(){
            HttpService.get("/allPhotos/" + $localStorage.storedObj.username).then(function (value) {
                vm.photoList = value;
                vm.showList = false;
            }, function (reason) {
                console.log("Error occured" + reason);
            });
        }

        function no(){
            $uibModalInstance.dismiss('close');
        }

    }
})()