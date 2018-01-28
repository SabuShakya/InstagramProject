(function () {
    angular.module("adminModule").controller("EditModalController",EditModalController);

    EditModalController.$inject = ['HttpService','$uibModalInstance','$rootScope','$location'];
    function EditModalController(HttpService,$uibModalInstance,$rootScope,$location) {
        var vm =  this;
        vm.admin = $rootScope.clickedAdmin;
        vm.url = "/update";
        vm.imageName = $rootScope.clickedAdmin.image;
        vm.cancel = cancel;
        vm.update = update;
        vm.deleteAdmin = deleteAdmin;
        vm.updatePhoto =updatePhoto;
        vm.ok = ok;

        function update() {
            $rootScope.clickedAdmin.image = null;
            HttpService.post("/update",vm.admin).then(
                function (value) {
                   $rootScope.message = "Updated successfully";
                   $rootScope.saved = true;
                    $uibModalInstance.close('save');
                },function (reason) {
                    $rootScope.message = "Error Occurred";
                    $rootScope.saved = true;
                });
        }

        function updatePhoto() {
            $rootScope.clickedAdmin.image = vm.imageName.base64;
            HttpService.post("/update",vm.admin).then(
                function (value) {
                    $rootScope.message = "Updated successfully";
                    $rootScope.saved = true;
                    $uibModalInstance.close('save');
                },function (reason) {
                    $rootScope.message = "Error Occurred";
                    $rootScope.saved = true;
                });
        }

        function deleteAdmin() {
            HttpService.post("/deleteAdmin",vm.admin).then(function (value) {
                $rootScope.message = "Deleted successfully";
                $rootScope.saved = true;
                $uibModalInstance.close('save');
            },function (reason) {
                $rootScope.message = "Error Occurred";
                $rootScope.saved = true;
            });
        }

        function cancel(){
            $uibModalInstance.dismiss('close');
        }

        function ok() {
            $location.path("/login");
            $uibModalInstance.dismiss();
        }
    }
})();